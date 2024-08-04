package br.com.fotoexpress.fotoexpress.pedido.model.mappers;

import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PedidoRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PedidoMapperFIx {

    public PedidoRequest getPedidoDTO(Pedido pedido) {
        return PedidoRequest
                .builder()
                .idCliente(pedido.getCliente().getId())
                .desconto(pedido.getDesconto())
                .build();
    }

}
