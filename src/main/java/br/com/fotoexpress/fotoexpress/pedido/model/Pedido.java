package br.com.fotoexpress.fotoexpress.pedido.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pedido {

    List<Pacote> pacotes;

}
