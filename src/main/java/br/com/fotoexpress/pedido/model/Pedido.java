package br.com.fotoexpress.pedido.model;

import br.com.fotoexpress.pedido.model.enums.StatusPedido;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "pedido")
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
  private double desconto;
  private double valor;
  private String idContrato;

  @ManyToMany
  @JoinTable(
      name = "pedido_pacotes",
      joinColumns = @JoinColumn(name = "id_pedido"),
      inverseJoinColumns = @JoinColumn(name = "id_pacote"))
  private List<Pacote> pacotes;

  @ManyToOne
  @JoinColumn(name = "id_cliente")
  private Cliente cliente;
}
