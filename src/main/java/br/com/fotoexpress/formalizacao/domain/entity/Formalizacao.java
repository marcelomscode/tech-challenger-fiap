package br.com.fotoexpress.formalizacao.domain.entity;

import br.com.fotoexpress.formalizacao.domain.enums.StatusFormalizacao;
import br.com.fotoexpress.pedido.model.Pedido;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "formalizacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Formalizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataFormalizacao;

    private String contratoId;

    @Enumerated(EnumType.STRING)
    private StatusFormalizacao statusFormalizacao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido pedido;

    public void formalizar() {
        this.statusFormalizacao = StatusFormalizacao.FORMALIZADO;
        this.dataFormalizacao = LocalDateTime.now();
    }

    public boolean assinarContrato() {
        if (this.statusFormalizacao == StatusFormalizacao.FORMALIZADO) {
            this.statusFormalizacao = StatusFormalizacao.ASSINADO;
            return true;
        }
        return false;
    }
}
