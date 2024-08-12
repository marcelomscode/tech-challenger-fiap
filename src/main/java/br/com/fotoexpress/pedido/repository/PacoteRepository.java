package br.com.fotoexpress.pedido.repository;

import br.com.fotoexpress.pedido.model.Pacote;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PacoteRepository extends JpaRepository<Pacote, Long> {

  @Query("SELECT p FROM Pacote p WHERE p.id IN :ids")
  List<Pacote> findByIds(@Param("ids") List<Integer> ids);
}
