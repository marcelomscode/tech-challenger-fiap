package br.com.fotoexpress.fotoexpress.pedido.services;

import br.com.fotoexpress.fotoexpress.pedido.model.Pacote;
import br.com.fotoexpress.fotoexpress.pedido.model.dto.PacoteDTO;
import br.com.fotoexpress.fotoexpress.pedido.model.enums.Acompanhamento;
import br.com.fotoexpress.fotoexpress.pedido.model.mappers.PacoteMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    public List<PacoteDTO> buscaPacotesDisponiveis() {

        List<PacoteDTO> pacoteDTO = getDTOSPacotes()
                .stream()
                .map(PacoteMapper.builder().build()::getPacoteDTO)
                .collect(Collectors.toList());

        return pacoteDTO;
    }

    private List<Pacote> getDTOSPacotes(){

        var pacote1 = Pacote
                .builder()
                .id(1)
                .nome("Fidelidade")
                .acompanhamento(Acompanhamento.MENSAL.getId())
                .descricao("Consiste em fotografar bebês até um ano para registros do crescimento mês a mês.")
                .valor(1000.00)
                .build();

        var pacote2 = Pacote
                .builder()
                .id(2)
                .nome("Fidelidade")
                .acompanhamento(Acompanhamento.BIMESTRAL.getId())
                .descricao("Consiste em fotografar bebês com a periodicidade de dois meses.")
                .valor(1000.00)
                .build();

        var pacote3 = Pacote
                .builder()
                .id(3)
                .nome("Fidelidade")
                .acompanhamento(Acompanhamento.TRIMESTRAL.getId())
                .descricao("Consiste em fotografar bebês com a periodicidade de três meses.")
                .valor(1000.00)
                .build();

        var pacote4 = Pacote
                .builder()
                .id(4)
                .nome("Gestante")
                .acompanhamento(Acompanhamento.GESTANTE.getId())
                .descricao("Acompanhamento mês a mês até o nascimento.")
                .valor(100.00)
                .build();

        var pacote5= Pacote
                .builder()
                .id(5)
                .nome("Sem Fidelidade")
                .acompanhamento(Acompanhamento.AVULSOS.getId())
                .descricao("Pacote para as mães que não desejam ter a fidelidade do estúdio.")
                .valor(100.00)
                .build();

        var pacote6 = Pacote
                .builder()
                .id(6)
                .nome("Extras")
                .acompanhamento(Acompanhamento.FAMILIA.getId())
                .descricao("Consiste na realização do ensaio com os membros da família.")
                .valor(50.00)
                .build();

        var pacote7 = Pacote
                .builder()
                .id(7)
                .nome("Extras")
                .acompanhamento(Acompanhamento.FAMILIAINDIVIDUAL.getId())
                .descricao("Consiste em incluir familiar individualmente pelo custo de R$ 50,00 cada.")
                .valor(50.00)
                .build();

        return List.of(pacote1, pacote2, pacote3, pacote4, pacote5, pacote6, pacote7);
    }

}
