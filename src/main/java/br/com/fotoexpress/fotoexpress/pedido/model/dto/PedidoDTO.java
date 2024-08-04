package br.com.fotoexpress.fotoexpress.pedido.model.dto;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Builder
public class PedidoDTO {

    @Pattern(regexp = "([0-9]{999999})")
    private Long id;
    @Pattern(regexp = "([0-9]{1})")
    private String status;
    @Pattern(regexp = "([0-9]{2})")
    private List<Integer> pacotes;
    @Pattern(regexp = "([0-9]{99999})")
    private Long idCliente;
    @Pattern(regexp = "\\d{1,3}(,\\d{3})*(\\.\\d{2})?")
    private double desconto;
    private double valor;
    private double valorTotal;

    public double getValorPacotes(List<PacoteDTO> pacotes) {

        double valor = 0;

        for (PacoteDTO pacote : pacotes) {
            valor += pacote.getValor();
        }

        return valor;
    }




}
