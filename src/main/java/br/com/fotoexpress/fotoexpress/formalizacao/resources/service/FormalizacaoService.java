package br.com.fotoexpress.fotoexpress.formalizacao.resources.service;

import br.com.fotoexpress.fotoexpress.formalizacao.resources.domain.entity.Formalizacao;
import br.com.fotoexpress.fotoexpress.formalizacao.resources.domain.enums.StatusFormalizacao;
import br.com.fotoexpress.fotoexpress.formalizacao.resources.dto.DocuSignRequestDTO;
import br.com.fotoexpress.fotoexpress.formalizacao.resources.dto.FormalizacaoDTO;
import br.com.fotoexpress.fotoexpress.formalizacao.resources.dto.FormalizacaoRequestDTO;
import br.com.fotoexpress.fotoexpress.formalizacao.resources.repository.FormalizacaoRepository;
import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.services.PedidoService;
import com.docusign.esign.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FormalizacaoService {
    @Autowired
    private FormalizacaoRepository formalizacaoRepository;

    @Autowired
    private DocuSignService docuSignService;

    @Autowired
    private ContratoPDFService contratoPDFService;

    @Autowired
    private PedidoService pedidoService;

    public FormalizacaoDTO save(FormalizacaoRequestDTO formalizacaoRequestDTO) throws IOException, ApiException {
        try {
            Formalizacao formalizacaoExistente = formalizacaoRepository.buscaFormalizacaoPorPedidoId(formalizacaoRequestDTO.pedidoId());
            if (formalizacaoExistente != null) {
                throw new RuntimeException("O pedido já possui uma formalização.");
            }

            Pedido pedido = pedidoService.buscaPedidoPorId(formalizacaoRequestDTO.pedidoId());
            byte[] contrato = contratoPDFService.get();
            String docuSignId = docuSignService.sendEnvelope(pedido.getCliente().getEmail(), pedido.getCliente().getNome(), contrato);

            Formalizacao formalizacao = new Formalizacao();
            formalizacao.setPedido(pedido);
            formalizacao.setContratoId(docuSignId);
            formalizacao.formalizar();

            formalizacaoRepository.save(formalizacao);
            return toFormalizacaoDTO(formalizacao);
        } catch (ApiException e) {
            throw new ApiException(500, "Erro ao enviar envelope DocuSign: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Erro ao buscar o arquivo");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar formalizar o pedido: " + e.getMessage());
        }
    }

    public FormalizacaoDTO assinarContrato(DocuSignRequestDTO docuSignRequestDTO) {
        try {
            Formalizacao formalizacao = formalizacaoRepository.buscaFormalizacaoPorContratoId(docuSignRequestDTO.envelopeId());
            if(formalizacao == null) {
                throw new RuntimeException("Não foi encontrado uma formalização para este contrato.");
            }
            formalizacao.assinarContrato();
            formalizacaoRepository.save(formalizacao);
            return toFormalizacaoDTO(formalizacao);
        }  catch (Exception e) {
            throw new RuntimeException("Erro ao tentar formalizar o pedido: " + e.getMessage());
        }
    }

    private FormalizacaoDTO toFormalizacaoDTO(Formalizacao formalizacao) {
        return new FormalizacaoDTO(
                formalizacao.getId(),
                formalizacao.getPedido().getId(),
                formalizacao.getPedido().getCliente().getNome(),
                formalizacao.getPedido().getCliente().getEmail(),
                formalizacao.getDataFormalizacao(),
                formalizacao.getContratoId(),
                formalizacao.getStatusFormalizacao()
        );
    }

    private Formalizacao toFormalizacao(FormalizacaoDTO formalizacaoDTO) {
        Pedido pedido = pedidoService.buscaPedidoPorId(formalizacaoDTO.pedidoId());
        return new Formalizacao(
                formalizacaoDTO.id(),
                formalizacaoDTO.dataFormalizacao(),
                formalizacaoDTO.contratoEnviadoId(),
                formalizacaoDTO.statusFormalizacao(),
                pedido
        );
    }
}
