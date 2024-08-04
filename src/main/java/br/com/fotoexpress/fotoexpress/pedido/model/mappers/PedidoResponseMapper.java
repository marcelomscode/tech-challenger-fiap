package br.com.fotoexpress.fotoexpress.pedido.model.mappers;

import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PedidoResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.stream.Collectors;

@Getter
@Setter
@Builder
public class PedidoResponseMapper {

    public PedidoResponse getPedidoDTO(Pedido pedido) {
        return PedidoResponse
                .builder()
                .id(pedido.getId())
                .status(pedido.getStatus().getDescricao())
                .pacotes(
                        pedido.getPacotes()
                                .stream()
                                .map(PacoteMapper.builder().build()::getPacoteDTO)
                                .collect(Collectors.toList()))
                .idCliente(pedido.getCliente().getId())
                .nomeCliente(pedido.getCliente().getNome())
                .desconto(pedido.getDesconto())
                .valor(pedido.getValor())
                .valorTotal(pedido.getValor() - pedido.getDesconto())
                .build();
    }

}
