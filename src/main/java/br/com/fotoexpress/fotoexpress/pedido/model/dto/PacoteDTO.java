package br.com.fotoexpress.fotoexpress.pedido.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PacoteDTO {

    private Long id;
    private String nome;
    private String acompanhamento;
    private String descricao;
    private double valor;

}
