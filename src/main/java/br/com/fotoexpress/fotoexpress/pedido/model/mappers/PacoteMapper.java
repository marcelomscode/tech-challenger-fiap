package br.com.fotoexpress.fotoexpress.pedido.model.mappers;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.enums.Acompanhamento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class PacoteMapper {

    public PacoteDTO getPacoteDTO(Pacote pacote) {
        return PacoteDTO
                .builder()
                .id(pacote.getId())
                .nome(pacote.getNome())
                .acompanhamento(Acompanhamento.getDescricaoById(pacote.getId()))
                .descricao(pacote.getDescricao())
                .valor(pacote.getValor())
                .build();
    }

    public Pacote getPacoteEntity(PacoteDTO pacoteDTO) {

        return Pacote
                .builder()
                .id(pacoteDTO.getId())
                .nome(pacoteDTO.getNome())
                .acompanhamento(pacoteDTO.getAcompanhamento())
                .descricao(pacoteDTO.getDescricao())
                .valor(pacoteDTO.getValor())
                .build();
    }

}
