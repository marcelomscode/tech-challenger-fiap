package br.com.fotoexpress.pedido.services;

import br.com.fotoexpress.exceptions.PedidoException;
import br.com.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.pedido.model.Pacote;
import br.com.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.pedido.model.dto.PedidoDTO;
import br.com.fotoexpress.pedido.model.dto.PedidoRequest;
import br.com.fotoexpress.pedido.model.enums.StatusPedido;
import br.com.fotoexpress.pedido.repository.ClienteRepository;
import br.com.fotoexpress.pedido.repository.PedidoRepository;
import br.com.fotoexpress.util.ConverterToDTO;
import jakarta.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PedidoService {

  private final PacotesService pacotesService;
  private final PedidoRepository pedidoRepository;
  private final ClienteRepository clienteRepository;
  private final ConverterToDTO converterToDTO;

  @Transactional(readOnly = true)
  public Page<PedidoDTO> findAll(Pageable pageable) {
    return pedidoRepository.findAll(pageable).map(converterToDTO::toDto);
  }

  @Transactional(readOnly = true)
  public Pedido findById(Long id) {
    return pedidoRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));
  }

  public PedidoDTO create(PedidoRequest pedidoDTO) {
    List<PacoteDTO> pacotes = pacotesService.findAllById(pedidoDTO.idPacotes());

    Cliente cliente =
        clienteRepository
            .findById(pedidoDTO.idCliente())
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

    Pedido pedido =
        Pedido.builder()
            .status(StatusPedido.EM_ANDAMENTO)
            .dataPedido(LocalDateTime.now())
            .pacotes(
                pacotes.stream()
                    .map(this::convertToPacote)
                    .collect(Collectors.toList()))
            .cliente(cliente)
            .valor(calculateTotalPackageValue(pacotes))
            .desconto(pedidoDTO.desconto())
            .build();

    try {
      pedidoRepository.save(pedido);
    } catch (PedidoException e) {
      log.error("Erro ao salvar pedido: {}", e.getMessage());
      throw new PedidoException(
          "Não foi possível salvar o pedido, erro: " + e.getMessage(), HttpStatus.NOT_FOUND);
    }

    return converterToDTO.toDto(pedido);
  }

  public void changeOrderStatus(Long idPedido, int status) {
    log.info("Iniciando a atualização do status do pedido com ID {}", idPedido);

    Pedido pedido =
        pedidoRepository
            .findById(idPedido)
            .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o ID " + idPedido));

    log.info("Pedido encontrado. Atualizando o status para {}", status);

    try {
      StatusPedido novoStatus = StatusPedido.getById(status);
      pedido.setStatus(novoStatus);

      log.info("Status do pedido atualizado para {}. Salvando pedido...", novoStatus);
      pedidoRepository.save(pedido);
      log.info("Pedido salvo com sucesso.");
    } catch (Exception e) {
      log.error("Erro ao atualizar o status do pedido com ID {}: {}", idPedido, e.getMessage());
      throw new PedidoException(
          "Não foi possível salvar o pedido. Erro: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public void updateContractIdForOrder(Long idPedido, String contratoId) {
    log.info("Iniciando a atualização do contrato do pedido com ID {}", idPedido);

    Pedido pedido =
        pedidoRepository
            .findById(idPedido)
            .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o ID " + idPedido));

    log.info("Pedido encontrado. Atualizando ID do contrato para {}", contratoId);

    try {
      pedido.setIdContrato(contratoId);
      pedidoRepository.save(pedido);
      log.info("ID do contrato atualizado e pedido salvo com sucesso.");
    } catch (Exception e) {
      log.error("Erro ao atualizar o pedido com ID {}: {}", idPedido, e.getMessage());
      throw new PedidoException(
          "Não foi possível salvar o pedido. Erro: " + e.getMessage(),
          HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private Pacote convertToPacote(PacoteDTO pacoteDTO) {
    return Pacote
            .builder()
            .id(pacoteDTO.id())
            .nome(pacoteDTO.nome())
            .acompanhamento(pacoteDTO.acompanhamento())
            .descricao(pacoteDTO.descricao())
            .valor(pacoteDTO.valor())
            .build();
  }

  private double calculateTotalPackageValue(List<PacoteDTO> pacotes) {
    double valor = 0;

    for (PacoteDTO pacote : pacotes) {
      valor += pacote.valor();
    }
    return valor;
  }
}
