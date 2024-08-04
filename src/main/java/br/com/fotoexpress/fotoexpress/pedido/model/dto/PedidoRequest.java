package br.com.fotoexpress.fotoexpress.pedido.model.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class PedidoRequest {

    @Pattern(regexp = "([0-9]{99999})")
    private Long idCliente;
    @Pattern(regexp = "([0-9]{2})")
    private List<Integer> idPacotes;
    @Pattern(regexp = "\\d{1,3}(,\\d{3})*(\\.\\d{2})?")
    private double desconto;

    public double getValorPacotes(List<PacoteDTO> pacotes) {
        double valor = 0;

        for (PacoteDTO pacote : pacotes) {
            valor += pacote.getValor();
        }
        return valor;
    }


}
