package br.com.fotoexpress.pedido.services;

import br.com.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.pedido.repository.PacoteRepository;
import br.com.fotoexpress.util.ConverterToDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PacotesService {

  private final PacoteRepository pacoteRepository;
  private final ConverterToDTO converterToDTO;

  @Autowired
  public PacotesService(PacoteRepository pacoteRepository, ConverterToDTO converterToDTO) {
    this.pacoteRepository = pacoteRepository;
    this.converterToDTO = converterToDTO;
  }

  public Page<PacoteDTO> findAll(Pageable pageable) {
    return pacoteRepository.findAll(pageable).map(converterToDTO::toDto);
  }

  public List<PacoteDTO> findAllById(List<Integer> ids) {
    return pacoteRepository.buscaListasPacotePorId(ids).stream()
        .map(converterToDTO::toDto)
        .collect(Collectors.toList());
  }
}
