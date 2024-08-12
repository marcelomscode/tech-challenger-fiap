package br.com.fotoexpress.pedido.model.dto;

import java.util.List;

public record PedidoDTO(
    Long id,
    String status,
    List<Integer> idPacotes,
    Long idCliente,
    String nomeCliente,
    double desconto,
    double valor,
    double valorTotal,
    List<PacoteDTO> pacotes) {

  public double getValorPacotes() {
    double valorPacotes = 0;

    for (PacoteDTO pacote : pacotes) {
      valorPacotes += pacote.valor();
    }
    return valorPacotes;
  }
}
