package br.com.fotoexpress.fotoexpress.pedido.resources;

import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PedidoRequest;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PedidoResponse;
import br.com.fotoexpress.fotoexpress.pedido.model.enums.StatusPedido;
import br.com.fotoexpress.fotoexpress.pedido.services.PacotesService;
import br.com.fotoexpress.fotoexpress.pedido.services.PedidoService;
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
@RequestMapping("/pedidos")
@Slf4j
public class PedidoResource {

    private PedidoService pedidoService;
    private PacotesService pacotesService;

    @Autowired
    public PedidoResource(PedidoService pedidoService, PacotesService pacotesService) {
        this.pedidoService = pedidoService;
        this.pacotesService = pacotesService;
    }

    @GetMapping()
    @Operation(summary = "Busca todos os pedidos cadastrados", description = "Busca uma listagem de pedidos ja cadastrados",
            responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = Pedido.class))}))
    public ResponseEntity<List<PedidoResponse>> listaPedidosCadastrados() {

        log.info("Inicio busca Pedidos");
        var pedidosCadastrados = pedidoService.buscaPedidosCadastrados();
        log.info("Fim busca Pedidos");

        return ResponseEntity.ok(pedidosCadastrados);
    }

    @GetMapping("/pacotes-disponiveis")
    @Operation(summary = "Lista os pacotes de fotos disponiveis", description = "Lista de pacotes de fotos que podem ser comercializados",
            responses = @ApiResponse(responseCode = "200", description = "Ok", content = {@Content(schema = @Schema(implementation = PacoteDTO.class))}))
    public ResponseEntity<List<PacoteDTO>> buscaPacotesDisponiveis() {

        log.info("Inicio de listar pacotes disponiveis");
        var pacotes = pacotesService.buscaTodosPacotesDisponiveis();
        log.info("Fim de listar pacotes disponiveis");

        return ResponseEntity.ok(pacotes);
    }

    @PostMapping
    @Operation(summary = "Cadastra um novo pedido", description = "Cadastra um novo pedido",
            responses = @ApiResponse(responseCode = "201", description = "Created", content = {@Content(schema = @Schema(implementation = String.class))}))
    public ResponseEntity<String> cadastraNovoPedido(
            @RequestBody PedidoRequest request) {

        log.info("Cadastrando novo pedido para o cliente: {}", request.getIdCliente());
        pedidoService.salvaPedido((request));
        log.info("Pedido cadastrado com sucesso, status do pedido: {}", StatusPedido.EM_ANDAMENTO);

        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido Cadastrado com Sucesso");
    }

}