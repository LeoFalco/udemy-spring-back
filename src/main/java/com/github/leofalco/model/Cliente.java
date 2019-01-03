package com.github.leofalco.model;

import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.model.enumerador.TipoCliente;
import com.github.leofalco.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static column.Def.com_github_leofalco_model_enumerador_TipoCliente;


@Entity
@Table(name = "cliente")
@Data
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 2, max = 50, message = "o descricao precisa ter entre 2 e 50 caracteres")
    private String nome;

    @Email
    @Column(unique = true)
    private String email;

    @CPF
    @Column(unique = true)
    private String inscricaoFederal;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = com_github_leofalco_model_enumerador_TipoCliente)
    private TipoCliente tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "telefone")
    @Column(name = "telefone")
    private List<String> telefones = new ArrayList<>();

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Long id, String nome, String email, String inscricaoFederal, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.inscricaoFederal = inscricaoFederal;
        this.tipo = tipo;
    }
}
