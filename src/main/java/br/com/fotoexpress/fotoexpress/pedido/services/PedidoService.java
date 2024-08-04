package br.com.fotoexpress.fotoexpress.pedido.services;

import br.com.fotoexpress.fotoexpress.pedido.model.Cliente;
import br.com.fotoexpress.fotoexpress.pedido.model.Pedido;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PedidoDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.enums.StatusPedido;
import br.com.fotoexpress.fotoexpress.pedido.model.mappers.PacoteMapper;
import br.com.fotoexpress.fotoexpress.pedido.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private PacotesService pacotesService;
    private ClienteService clienteService;
    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PacotesService pacotesService,
                         ClienteService clienteService,
                         PedidoRepository pedidoRepository) {
        this.pacotesService = pacotesService;
        this.clienteService = clienteService;
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> buscaPedidosCadastrados() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos;
    }

    public Pedido salvaPedido(PedidoDTO pedidoDTO) {

        List<PacoteDTO> pacotes = pacotesService.buscaListaPacotesPorId(pedidoDTO.getPacotes());

        Cliente cliente = clienteService.buscaClientePorId(pedidoDTO.getIdCliente());

        Pedido pedido = Pedido
                .builder()
                .status(StatusPedido.EM_ANDAMENTO)
                .dataPedido(LocalDateTime.now())
                .pacotes(
                        pacotes
                                .stream()
                                .map(PacoteMapper.builder().build()::getPacoteEntity)
                                .collect(Collectors.toList()))
                .cliente(cliente)
                .valor(pedidoDTO.getValorPacotes(pacotes))
                .desconto(pedidoDTO.getDesconto())
                .build();
        try {
            pedido = pedidoRepository.save(pedido);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pedido;
    }
}
