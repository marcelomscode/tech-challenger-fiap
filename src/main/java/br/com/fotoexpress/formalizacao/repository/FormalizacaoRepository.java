package br.com.fotoexpress.formalizacao.repository;

import br.com.fotoexpress.formalizacao.domain.entity.Formalizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormalizacaoRepository extends JpaRepository<Formalizacao, Long> {

  @Query("SELECT f FROM Formalizacao f WHERE f.pedido.id = :pedidoId")
  Formalizacao findByPedidoId(@Param("pedidoId") Long pedidoId);

  @Query("SELECT f FROM Formalizacao f WHERE f.contratoId = :contratoId")
  Formalizacao findByContratoId(@Param("contratoId") String contratoId);
}
