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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/formalizacao")
public class FormalizacaoResource {
    @Autowired
    private FormalizacaoService formalizacaoService;

    @GetMapping
    public String get() {
        return "OK podemos comecar as formalizacoes";
    }

    @PostMapping
    @Operation(summary = "Cria uma nova formalização para o pedido", description = "Cria nova formalização para o pedido",
            responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = String.class))}))
    public ResponseEntity<FormalizacaoDTO> save(@RequestBody FormalizacaoRequestDTO formalizacaoRequestDTO) throws IOException, ApiException {
        FormalizacaoDTO formalizacaoDTO = formalizacaoService.save(formalizacaoRequestDTO);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(formalizacaoDTO);
    }

    @PostMapping("contrato-assinado")
    public ResponseEntity<FormalizacaoDTO> assinarContrato(@RequestBody DocusignRequestDTO docuSignRequestDTO) {
        FormalizacaoDTO formalizacaoDTO = formalizacaoService.assinarContrato(docuSignRequestDTO);
        return  ResponseEntity.status(HttpStatusCode.valueOf(200)).body(formalizacaoDTO);
    }
}
