package com.github.leofalco;

import com.github.leofalco.model.Categoria;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.Produto;
import com.github.leofalco.model.endereco.Cidade;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.model.endereco.Estado;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.enumerador.TipoCliente;
import com.github.leofalco.model.pagamento.Pagamento;
import com.github.leofalco.model.pagamento.PagamentoBoleto;
import com.github.leofalco.model.pagamento.PagamentoCartao;
import com.github.leofalco.model.pedido.ItemPedido;
import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.repository.*;
import com.github.leofalco.util.Dates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class MockTestDataService {

    private static boolean dadosCarregados = false;

    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final EstadoRepository estadoRepository;
    private final CidadeRepository cidadeRepository;
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;
    private final PagamentoRepository pagamentoRepository;
    private final ItemPedidoRepository itemPedidoRepository;
    private final PedidoRepository pedidoRepository;


    @Autowired
    public MockTestDataService(CategoriaRepository categoriaRepository,
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
        this.pagamentoRepository = pagamentoRepository;
        this.itemPedidoRepository = itemPedidoRepository;
        this.pedidoRepository = pedidoRepository;
    }

    public void mock() {

        if (dadosCarregados) {
            System.out.println("Dados já foram carregados, os dados podem ser carregados somente uma ves");
            return;
        }
        dadosCarregados = true;

        System.out.println("Carregando instancia dos dados");

        Categoria cat1 = new Categoria(null, "Informática");
        Categoria cat2 = new Categoria(null, "Escritório");
        Categoria cat3 = new Categoria(null, "Roupas");
        Categoria cat4 = new Categoria(null, "Higiene");
        Categoria cat5 = new Categoria(null, "Acessorios");
        Categoria cat6 = new Categoria(null, "Acampamento");
        Categoria cat7 = new Categoria(null, "Perfumaria");

        Produto p1 = new Produto(null, "Computador", new BigDecimal("2000"));
        Produto p2 = new Produto(null, "Impressora", new BigDecimal("800"));
        Produto p3 = new Produto(null, "Mouse", new BigDecimal("80"));


        cat1.setProdutos(Arrays.asList(p1, p2, p3));
        cat2.setProdutos(Collections.singletonList(p2));

        p1.setCategorias(Collections.singletonList(cat1));
        p2.setCategorias(Arrays.asList(cat1, cat2));
        p3.setCategorias(Collections.singletonList(cat1));


        Estado mg = new Estado("MG", "Minas Gerais");
        Estado sp = new Estado("SP", "São Paulo");
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
                mg,
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
                sp,
                new Estado("TO", "Tocantins")
        );


        Cidade uberlandia = new Cidade(null, "Uberlândia", mg);
        Cidade saoPaulo = new Cidade(null, "São Paulo", sp);
        Cidade campinas = new Cidade(null, "Campinas", sp);

        mg.getCidades().add(uberlandia);
        sp.getCidades().addAll(Arrays.asList(saoPaulo, campinas));


        Cliente marioSilva = new Cliente(null, "Mario Silva", "maria@gmailcom", "46849145851", TipoCliente.PESSOA_FISICA);
        marioSilva.getTelefones().addAll(Arrays.asList("1254778914", "458799641"));

        Endereco e1 = new Endereco(null, "Rua flores", "300", "apto 203", "jardim jardim", "38220834", uberlandia, marioSilva);
        Endereco e2 = new Endereco(null, "Avenida Mattos", "105", "sala 800", "Centro", "3877012", saoPaulo, marioSilva);


        Pedido ped1 = new Pedido(null, Dates.toDate("01/01/2017 05:10"), marioSilva, e1);
        Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
        ped1.setPagamento(pagto1);

        Pedido ped2 = new Pedido(null, Dates.toDate("10/10/2017 19:35"), marioSilva, e2);
        Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, Dates.toLocalDateTime("20/10/2017 19:35"), null);
        ped2.setPagamento(pagto2);

        marioSilva.getPedidos().addAll(Arrays.asList(ped1, ped2));


        ItemPedido ip1 = new ItemPedido(ped1, p1, BigDecimal.ZERO, 1, new BigDecimal("2000"));
        ItemPedido ip2 = new ItemPedido(ped1, p3, BigDecimal.ZERO, 1, new BigDecimal("80"));
        ItemPedido ip3 = new ItemPedido(ped2, p2, new BigDecimal("100"), 1, new BigDecimal("800"));

        ped1.getItens().addAll(Arrays.asList(ip1, ip2));
        ped2.getItens().add(ip3);

        p1.getItens().add(ip1);
        p2.getItens().add(ip3);
        p3.getItens().add(ip2);


        estadoRepository.saveAll(estadoList);
        cidadeRepository.saveAll(Arrays.asList(uberlandia, saoPaulo, campinas));
        clienteRepository.save(marioSilva);
        enderecoRepository.saveAll(Arrays.asList(e1, e2));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
        pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
        itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));

    }

}
