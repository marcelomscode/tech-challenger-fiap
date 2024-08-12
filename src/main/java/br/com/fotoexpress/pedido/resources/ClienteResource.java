package br.com.fotoexpress.pedido.resources;

import br.com.fotoexpress.dto.ClienteDTO;
import br.com.fotoexpress.dto.ClienteForm;
import br.com.fotoexpress.pedido.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@Api(value = "Client Controller")
@RequiredArgsConstructor
@Slf4j
public class ClienteResource {

  private final ClienteService clienteService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Busca todos os clientes cadastrados",
      description = "Busca uma listagem de clientes ja cadastrados",
      responses =
          @ApiResponse(
              responseCode = "200",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = ClienteDTO.class))}))
  public ResponseEntity<Page<ClienteDTO>> findAll(@PageableDefault Pageable pageable) {
    return ResponseEntity.ok(clienteService.findAll(pageable));
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
    return ResponseEntity.ok(clienteService.findById(id));
  }

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Cadastra um novo cliente",
      description = "Cadastra um novo cliente",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = ClienteDTO.class))}))
  public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO dto) {
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(dto));
  }

  @PutMapping(
      value = "/{id}",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ClienteDTO> update(
      @PathVariable Long id, @RequestBody ClienteForm clienteForm) {
    return ResponseEntity.ok(clienteService.update(id, clienteForm));
  }

  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clienteService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
