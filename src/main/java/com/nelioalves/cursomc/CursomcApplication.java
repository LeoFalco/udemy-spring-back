package com.nelioalves.cursomc;

import com.nelioalves.cursomc.enumerator.model.EstadoPagamento;
import com.nelioalves.cursomc.model.*;
import com.nelioalves.cursomc.model.enumerador.TipoCliente;
import com.nelioalves.cursomc.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PedidoRepository pedidoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Autowired
    public CursomcApplication(CategoriaRepository categoriaRepository,
                              ProdutoRepository produtoRepository,
                              EstadoRepository estadoRepository,
                              CidadeRepository cidadeRepository,
                              ClienteRepository clienteRepository,
                              EnderecoRepository enderecoRepository,
                              PedidoRepository pedidoRepository,
                              PagamentoRepository pagamentoRepository,
                              ItemPedidoRepository itemPedidoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.estadoRepository = estadoRepository;
        this.cidadeRepository = cidadeRepository;
        this.clienteRepository = clienteRepository;
        this.enderecoRepository = enderecoRepository;
        this.pedidoRepository = pedidoRepository;
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) {


        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");

        Produto p1 = new Produto(null, "Computador", new BigDecimal("2000"));
        Produto p2 = new Produto(null, "Impressora", new BigDecimal("800"));
        Produto p3 = new Produto(null, "Mouse", new BigDecimal("80"));


        cat1.setProdutos(Arrays.asList(p1, p2, p3));
        cat2.setProdutos(Collections.singletonList(p2));

        p1.setCategorias(Collections.singletonList(cat1));
        p2.setCategorias(Arrays.asList(cat1, cat2));
        p3.setCategorias(Collections.singletonList(cat1));


        List<Estado> estadoList = Arrays.asList(
                new Estado("AC", "Acre"),
                new Estado("AL", "Alagoas"),
                new Estado("AM", "Amazonas"),
                new Estado("AP", "Amapá"),
                new Estado("BA", "Bahia"),
                new Estado("CE", "Ceará"),
                new Estado("DF", "Distrito Federal"),
                new Estado("ES", "Espírito Santo"),
                new Estado("GO", "Goiás"),
                new Estado("MA", "Maranhão"),
                new Estado("MG", "Minas Gerais"),
                new Estado("MS", "Mato Grosso do Sul"),
                new Estado("MG", "Mato Grosso"),
                new Estado("PA", "Pará"),
                new Estado("PB", "Paraíba"),
                new Estado("PE", "Pernambuco"),
                new Estado("PI", "Piauí"),
                new Estado("PR", "Paraná"),
                new Estado("RJ", "Rio de Janeiro"),
                new Estado("RN", "Rio Grande do Norte"),
                new Estado("RO", "Rondônia"),
                new Estado("RR", "Roraima"),
                new Estado("RS", "Rio Grande do Sul"),
                new Estado("SC", "Santa Catarina"),
                new Estado("SE", "Sergipe"),
                new Estado("SP", "São Paulo"),
                new Estado("TO", "Tocantins")
        );


        Estado est1 = estadoList.stream()
                .filter(estado -> (estado.getUf().equals("MG")))
                .findFirst()
                .get();

        Estado est2 = estadoList.stream()
                .filter(estado -> (estado.getUf().equals("SP")))
                .findFirst()
                .get();

        System.out.println(est1.getUf());
        System.out.println(est2.getUf());


        Cidade c1 = new Cidade(null, "Uberlândia", est1);
        Cidade c2 = new Cidade(null, "São Paulo", est2);
        Cidade c3 = new Cidade(null, "Campinas", est2);

        est1.getCidades().add(c1);
        est2.getCidades().addAll(Arrays.asList(c2, c3));


        Cliente cli1 = new Cliente(null, "Mario Silva", "maria@gmailcom", "36378912377", TipoCliente.PESSOA_FISICA);
        cli1.getTelefones().addAll(Arrays.asList("1254778914", "458799641"));

        Endereco e1 = new Endereco(null, "Rua flores", 300, "apto 203", "jardim jardim", "38220834", c1, cli1);
        Endereco e2 = new Endereco(null, "Avenida Mattos", 105, "sala 800", "Centro", "3877012", c2, cli1);


        Pedido ped1 = new Pedido(null, Dates.toDate("01/01/2017 05:10"), cli1, e1);
        Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pedido ped2 = new Pedido(null, Dates.toDate("10/10/2017 19:35"), cli1, e2);
        Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, Dates.toDate("20/10/2017 19:35"), null);
        ped2.setPagamento(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));


        ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.ZERO, 1, new BigDecimal("2000"));
        ItemPedido ip2 = new ItemPedido(ped1, p3, BigDecimal.ZERO, 1, new BigDecimal("80"));
        ItemPedido ip3 = new ItemPedido(ped2, p2, new BigDecimal("100"), 1, new BigDecimal("800"));

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);


        estadoRepository.saveAll(estadoList);
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        clienteRepository.save(cli1);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));


        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));




        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));


    }
}
