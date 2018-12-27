package com.github.leofalco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.leofalco.dto.ClienteDTO;
import com.github.leofalco.interfaces.AsDTO;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.model.enumerador.TipoCliente;
import com.github.leofalco.model.pedido.Pedido;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente implements Serializable, AsDTO<ClienteDTO> {
    private static final long serialVersionUID = 1L;

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
    private TipoCliente tipo;

    @OneToMany(mappedBy = "cliente")
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "telefone")
    @Column(name = "telefone")
    private Set<String> telefones = new HashSet<>();

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Long id, String nome, String email, String inscricaoFederal, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.inscricaoFederal = inscricaoFederal;
        this.tipo = tipo;
    }

    @Override
    public ClienteDTO asDTO() {
        return new ClienteDTO();
    }
}
