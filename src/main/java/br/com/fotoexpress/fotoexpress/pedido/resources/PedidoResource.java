package br.com.fotoexpress.fotoexpress.pedido.resources;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
@Slf4j
public class PedidoResource {

    @GetMapping
    @Operation(summary = "Busca todos os pedidos de fotos com todos os status", description = "Todos os status dos pedidos de fotos serão exibidos nesse endpoint",
            responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = String.class))}))
    public String get() {
        log.info("Entrou no get");
        return "OK podemos comecar os pedidos";
    }

    @GetMapping("/pacotes")
    @Operation(summary = "Lista os pacotes disponiveis", description = "Lista de pacotes de fotos que podem ser comercializados",
            responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = String.class))}))
    public ResponseEntity<String> buscaPacotesDisponiveis() {
        log.info("Inicio de listar pacotes disponiveis");

        return ResponseEntity.ok("Listando os pacotes disponiveis");
    }

}