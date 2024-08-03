package br.com.fotoexpress.fotoexpress.pedido.repository;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacoteRepository extends JpaRepository<Pacote, Long> {
}
