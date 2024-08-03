package br.com.fotoexpress.fotoexpress.pedido.model;


import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "pacote", schema = "fotoexpress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pacote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String nome;
    private String acompanhamento;
    private String descricao;
    private double valor;


}
