package br.com.fotoexpress.fotoexpress.pedido.model;

import br.com.fotoexpress.fotoexpress.pedido.model.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedido", schema = "fotoexpress")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataPedido;
    private StatusPedido status;

    @OneToMany
    private List<Pacote> pacotes;

    @ManyToOne
    private Cliente cliente;


}
