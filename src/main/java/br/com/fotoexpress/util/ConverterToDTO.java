package br.com.fotoexpress.util;

import br.com.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.pedido.model.Pacote;
import br.com.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.pedido.model.dto.ClienteDTO;
import br.com.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.pedido.model.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterToDTO {

  private final ModelMapper modelMapper;

  public ClienteDTO toDto(final Cliente model) {
    return modelMapper.map(model, ClienteDTO.class);
  }

  public PedidoDTO toDto(final Pedido model) {
    return modelMapper.map(model, PedidoDTO.class);
  }

  public PacoteDTO toDto(final Pacote model) {
    return modelMapper.map(model, PacoteDTO.class);
  }



}
