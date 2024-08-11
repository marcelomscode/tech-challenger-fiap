package br.com.fotoexpress.formalizacao.service;

import br.com.fotoexpress.exceptions.FormalizacaoException;
import br.com.fotoexpress.exceptions.PedidoException;
import br.com.fotoexpress.formalizacao.domain.entity.Formalizacao;
import br.com.fotoexpress.formalizacao.dto.DocuSignRequestDTO;
import br.com.fotoexpress.formalizacao.dto.FormalizacaoDTO;
import br.com.fotoexpress.formalizacao.dto.FormalizacaoRequestDTO;
import br.com.fotoexpress.formalizacao.repository.FormalizacaoRepository;
import br.com.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.pedido.model.enums.StatusPedido;
import br.com.fotoexpress.pedido.services.PedidoService;
import com.docusign.esign.client.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
            if(pedido == null) {
                throw  new PedidoException("Pedido não encontrado pelo id, " + formalizacaoRequestDTO.pedidoId(), HttpStatus.NOT_FOUND);
            }

            byte[] contrato = contratoPDFService.get();
            String docuSignId = docuSignService.sendEnvelope(pedido.getCliente().getEmail(), pedido.getCliente().getNome(), contrato);

            Formalizacao formalizacao = new Formalizacao();
            formalizacao.setPedido(pedido);
            formalizacao.setContratoId(docuSignId);
            formalizacao.formalizar();

            formalizacaoRepository.save(formalizacao);

            pedidoService.adicionarContratoIdaoPedido(formalizacaoRequestDTO.pedidoId(), docuSignId);

            return toFormalizacaoDTO(formalizacao);
        } catch (ApiException e) {
            throw new ApiException(500, "Erro ao enviar envelope DocuSign: " + e.getMessage());
        } catch (IOException e) {
            throw new IOException("Erro ao buscar o arquivo");
        } catch (FormalizacaoException e) {
            throw new FormalizacaoException("Erro ao tentar formalizar o pedido: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public FormalizacaoDTO assinarContrato(DocuSignRequestDTO docuSignRequestDTO) {
        try {
            Formalizacao formalizacao = formalizacaoRepository.buscaFormalizacaoPorContratoId(docuSignRequestDTO.envelopeId());
            if(formalizacao == null) {
                throw new FormalizacaoException("Não foi encontrado uma formalização para este contrato.", HttpStatus.NOT_FOUND);
            }
            formalizacao.assinarContrato();
            formalizacaoRepository.save(formalizacao);
            pedidoService.mudaStatusPedido(formalizacao.getPedido().getId(), StatusPedido.AGENDAR.getId());

            return toFormalizacaoDTO(formalizacao);
        }  catch (FormalizacaoException e) {
            throw new FormalizacaoException("Erro ao tentar formalizar o pedido: " + e.getMessage(), HttpStatus.NOT_FOUND);
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
