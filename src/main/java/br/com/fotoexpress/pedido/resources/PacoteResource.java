package br.com.fotoexpress.pedido.resources;

import br.com.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.pedido.services.PacotesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pacote")
@RequiredArgsConstructor
public class PacoteResource {

  private final PacotesService pacotesService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Lista os pacotes de fotos disponiveis",
      description = "Lista de pacotes de fotos que podem ser comercializados",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = PacoteDTO.class))}))
  public ResponseEntity<Page<PacoteDTO>> findAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(pacotesService.findAll(pageable));
  }
}
