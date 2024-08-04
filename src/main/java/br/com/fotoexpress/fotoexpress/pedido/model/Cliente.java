package br.com.fotoexpress.fotoexpress.pedido.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name= "cliente",schema = "fotoexpress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String nome;
    String email;
    String telefone;
    String cpf;

}
