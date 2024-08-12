package br.com.fotoexpress.pedido.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cliente", schema = "fotoexpress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nome;

  private String email;

  private String telefone;

  private String cpf;
}
