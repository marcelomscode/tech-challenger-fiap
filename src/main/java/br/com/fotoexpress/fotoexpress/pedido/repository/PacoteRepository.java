package br.com.fotoexpress.fotoexpress.pedido.repository;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, Long> {

    @Query("select p from Pacote p where p.id in :ids")
    List<Pacote> buscaListasPacotePorId(List<Integer> ids);

}
