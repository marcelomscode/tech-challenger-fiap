package br.com.fotoexpress.fotoexpress.pedido.resources;

import br.com.fotoexpress.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.fotoexpress.pedido.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
@Slf4j
public class ClienteResource {

    private final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    @Operation(summary = "Busca todos os clientes cadastrados", description = "Busca uma listagem de clientes ja cadastrados",
            responses = @ApiResponse(responseCode = "200",
                    description = "Ok",
                    content = {@Content(schema = @Schema(implementation = Cliente.class))}))
    public ResponseEntity<List<Cliente>> listaClientesCadastrados() {

        log.info("Inicio busca Clientes");
        var clientesCadastrados = clienteService.buscaListadeClientes();
        log.info("Fim busca Clientes");

        return ResponseEntity.ok(clientesCadastrados);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo cliente", description = "Cadastra um novo cliente",
            responses = @ApiResponse(responseCode = "201",
                    description = "Ok",
                    content = {@Content(schema = @Schema(implementation = Cliente.class))}))
    public ResponseEntity<String> salvaCliente(@RequestBody Cliente cliente) {

        log.info("Cadastrando novo cliente");
        clienteService.salvaCliente(cliente);
        log.info("Cliente cadastrado com sucesso, {}", cliente.getNome());

        return ResponseEntity.status(HttpStatus.CREATED).body ("Novo Cliente salvo com sucesso.");
    }

}
