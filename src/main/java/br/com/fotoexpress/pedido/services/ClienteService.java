package br.com.fotoexpress.pedido.services;

import br.com.fotoexpress.pedido.model.dto.ClienteDTO;
import br.com.fotoexpress.pedido.model.dto.ClienteRequest;
import br.com.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.pedido.repository.ClienteRepository;
import br.com.fotoexpress.util.ConverterToDTO;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ClienteService {

  private final ClienteRepository clienteRepository;
  private final ConverterToDTO converterToDTO;

  @Transactional(readOnly = true)
  public ClienteDTO findById(Long id) {
    return clienteRepository
        .findById(id)
        .map(converterToDTO::toDto)
        .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
  }

  @Transactional(readOnly = true)
  public Page<ClienteDTO> findAll(Pageable pageable) {
    log.info("Buscando todos os clientes");
    return clienteRepository.findAll(pageable).map(converterToDTO::toDto);
  }

  public ClienteDTO create(ClienteDTO dto) {
    Cliente cliente =
        Cliente.builder()
            .cpf(dto.cpf())
            .email(dto.email())
            .telefone(dto.telefone())
            .nome(dto.nome())
            .build();

    return converterToDTO.toDto(clienteRepository.save(cliente));
  }

  public ClienteDTO update(Long id, ClienteRequest clienteRequest) {
    Cliente cliente =
        clienteRepository
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

    cliente.setCpf(clienteRequest.cpf());
    cliente.setEmail(clienteRequest.email());
    cliente.setTelefone(clienteRequest.telefone());
    cliente.setNome(clienteRequest.nome());

    return converterToDTO.toDto(clienteRepository.save(cliente));
  }

  public void delete(Long id) {
    clienteRepository.deleteById(id);
  }
}
