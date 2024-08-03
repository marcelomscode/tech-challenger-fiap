package br.com.fotoexpress.fotoexpress.pedido.services;

import br.com.fotoexpress.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.enums.StatusPedido;
import br.com.fotoexpress.fotoexpress.pedido.model.mappers.PacoteMapper;
import br.com.fotoexpress.fotoexpress.pedido.repository.ClienteRepository;
import br.com.fotoexpress.fotoexpress.pedido.repository.PacoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private PacotesService pacotesService;
    private ClienteService clienteService;

    @Autowired
    public PedidoService(PacotesService pacotesService, ClienteService clienteService) {
        this.pacotesService = pacotesService;
        this.clienteService = clienteService;
    }

    public List<Pedido> buscaPedidosCadastrados() {

        Pedido pedidos = Pedido
                .builder()
                .id(1L)
                .cliente(clienteService.buscaClientePorId(1L))
                .status(StatusPedido.EM_ANDAMENTO)
                .dataPedido(LocalDateTime.now())
                .pacotes(
                        pacotesService.buscaTodosPacotesDisponiveis().stream()
                                .map(PacoteMapper.builder().build()::getPacoteEntity).collect(Collectors.toList()))
                .build();

        return List.of(pedidos);
    }

}
