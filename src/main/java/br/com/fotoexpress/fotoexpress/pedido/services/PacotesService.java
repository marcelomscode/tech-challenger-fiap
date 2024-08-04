package br.com.fotoexpress.fotoexpress.pedido.services;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.mappers.PacoteMapper;
import br.com.fotoexpress.fotoexpress.pedido.repository.PacoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PacotesService {


    private PacoteRepository pacoteRepository;

    public PacotesService(PacoteRepository pacoteRepository) {
        this.pacoteRepository = pacoteRepository;
    }

    public List<PacoteDTO> buscaTodosPacotesDisponiveis() {
        List<Pacote> pacotes = pacoteRepository.findAll();

        List<PacoteDTO> pacoteDTO = pacotes
                .stream()
                .map(PacoteMapper.builder().build()::getPacoteDTO)
                .collect(Collectors.toList());
        return pacoteDTO;
    }

    public List<PacoteDTO> buscaListaPacotesPorId(List<Integer> ids) {

        List<Pacote> pacotes = pacoteRepository.buscaListasPacotePorId(ids);

        List<PacoteDTO> pacotesDTO = pacotes
                .stream()
                .map(PacoteMapper.builder().build()::getPacoteDTO)
                .collect(Collectors.toList());

        return pacotesDTO;
    }


}