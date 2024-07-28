package br.com.fotoexpress.fotoexpress.pedido.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pacote {

    private int id;
    private String nome;
    private int acompanhamento;
    private String descricao;
    private double valor;


}
