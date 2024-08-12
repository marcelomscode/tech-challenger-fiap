package br.com.fotoexpress.pedido.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class PedidoResponse {

    private Long id;
    private String status;
    private Long idCliente;
    private String nomeCliente;
    private double desconto;
    private double valor;
    private double valorTotal;
    private LocalDateTime dataPedido;
    private List<PacoteDTO> pacotes;

    public double getValorPacotes(List<PacoteDTO> pacotes) {
        double valorPcotes = 0;

        for (PacoteDTO pacote : pacotes) {
            valorPcotes += pacote.getValor();
        }
        return valorPcotes;
    }
}
