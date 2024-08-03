package br.com.fotoexpress.fotoexpress.pedido.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

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

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos = new HashSet<>();


}
