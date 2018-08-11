package com.nelioalves.cursomc;

import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.model.Cidade;
import com.nelioalves.cursomc.model.Estado;
import com.nelioalves.cursomc.model.Produto;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import com.nelioalves.cursomc.repository.CidadeRepository;
import com.nelioalves.cursomc.repository.EstadoRepository;
import com.nelioalves.cursomc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public static void main(String[] args) {
        SpringApplication.run(CursomcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
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

        estadoRepository.saveAll(estadoList);
        cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
        categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
        produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

    }
}
