package com.nelioalves.cursomc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nelioalves.cursomc.model.enumerador.TipoCliente;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String email;
    private String inscricaoFederal;
    private TipoCliente tipo;
    private List<Endereco> enderecos = new ArrayList<>();
    private Set<String> telefones = new HashSet<>();
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(Integer id, String nome, String email, String inscricaoFederal, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.inscricaoFederal = inscricaoFederal;
        this.tipo = tipo;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInscricaoFederal() {
        return inscricaoFederal;
    }

    public void setInscricaoFederal(String inscricaoFederal) {
        this.inscricaoFederal = inscricaoFederal;
    }

    @Enumerated(EnumType.STRING)
    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    @OneToMany(mappedBy = "cliente")
    @JsonManagedReference
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @ElementCollection
    @CollectionTable(name = "telefone")
    @Column(name = "telefone")
    public Set<String> getTelefones() {
        return telefones;
    }

    public void setTelefones(Set<String> telefones) {
        this.telefones = telefones;
    }

    @OneToMany(mappedBy = "cliente")
    @JsonBackReference
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", inscricaoFederal='" + inscricaoFederal + '\'' +
                ", tipo=" + tipo +
                ", enderecos=" + enderecos +
                ", telefones=" + telefones +
                ", pedidos=" + pedidos +
                '}';
    }
}
