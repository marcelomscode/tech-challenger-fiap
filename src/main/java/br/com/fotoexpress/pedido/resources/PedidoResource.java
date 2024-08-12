package br.com.fotoexpress.pedido.resources;

import br.com.fotoexpress.pedido.model.dto.PedidoDTO;
import br.com.fotoexpress.pedido.model.dto.PedidoRequest;
import br.com.fotoexpress.pedido.services.PedidoService;
import br.com.fotoexpress.util.ConverterToDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pedidos")
@RequiredArgsConstructor
public class PedidoResource {

  private final PedidoService pedidoService;
  private final ConverterToDTO converterToDTO;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Lista todos os pedidos cadastrados",
      description = "Busca uma listagem de pedidos ja cadastrados",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = PedidoDTO.class))}))
  public ResponseEntity<Page<PedidoDTO>> findAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(pedidoService.findAll(pageable));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(converterToDTO.toDto(pedidoService.findById(id)));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Cadastra um novo pedido",
      description = "Cadastra um novo pedido",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Created",
              content = {@Content(schema = @Schema(implementation = String.class))}))
  public ResponseEntity<PedidoDTO> create(@RequestBody PedidoRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.create((request)));
  }

  @PutMapping(
      value = "/{idpacote}/{status}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Muda o status do pedido",
      description = "Muda o status do pedido quando um pedido for formalizado.")
  @ApiResponses(
      value = {
        @ApiResponse(responseCode = "200", description = "Ok"),
        @ApiResponse(responseCode = "404", description = "Not Found")
      })
  public ResponseEntity<String> changeOrderStatus(
      @PathVariable("idpacote") Long idPacote, @PathVariable("status") int status) {

    pedidoService.changeOrderStatus(idPacote, status);

    return ResponseEntity.ok().body("Estatus do pedido alterado com sucesso.");
  }
}
