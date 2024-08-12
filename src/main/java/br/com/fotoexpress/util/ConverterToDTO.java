package br.com.fotoexpress.util;

import br.com.fotoexpress.dto.ClienteDTO;
import br.com.fotoexpress.pedido.model.Cliente;
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
}
