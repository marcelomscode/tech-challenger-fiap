package br.com.fotoexpress.formalizacao.resources;

import br.com.fotoexpress.formalizacao.dto.DocusignRequestDTO;
import br.com.fotoexpress.formalizacao.dto.FormalizacaoDTO;
import br.com.fotoexpress.formalizacao.dto.FormalizacaoRequestDTO;
import br.com.fotoexpress.formalizacao.service.FormalizacaoService;
import com.docusign.esign.client.ApiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/formalizacao")
@RequiredArgsConstructor
public class FormalizacaoResource {

  private final FormalizacaoService formalizacaoService;

  @PostMapping(
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Cria uma nova formalização para o pedido",
      description = "Cria nova formalização para o pedido",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = FormalizacaoDTO.class))}))
  public ResponseEntity<FormalizacaoDTO> create(
      @RequestBody FormalizacaoRequestDTO formalizacaoRequestDTO) throws IOException, ApiException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(formalizacaoService.create(formalizacaoRequestDTO));
  }

  @PostMapping(
      value = "/contrato-assinado",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Assina o contrato",
      description = "Assina o contrato",
      responses =
          @ApiResponse(
              responseCode = "201",
              description = "Ok",
              content = {@Content(schema = @Schema(implementation = FormalizacaoDTO.class))}))
  public ResponseEntity<FormalizacaoDTO> signContract(
      @RequestBody DocusignRequestDTO docuSignRequestDTO) {
    return ResponseEntity.ok(formalizacaoService.signContract(docuSignRequestDTO));
  }
}
