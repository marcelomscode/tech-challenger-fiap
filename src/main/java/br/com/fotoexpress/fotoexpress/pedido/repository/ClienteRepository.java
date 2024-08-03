package br.com.fotoexpress.fotoexpress.pedido.repository;

import br.com.fotoexpress.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findById(Long id);

}
