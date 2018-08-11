package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.model.enumerador.TipoCliente;

import javax.persistence.*;


@Entity
@Table(name = "pedido")
public class Pedido {

    private Integer id;
    private String nome;
    private String email;
    private String inscricaoFederal;
    private TipoCliente tipo;

    public Pedido() {
    }

    public Pedido(Integer id, String nome, String email, String inscricaoFederal, TipoCliente tipo) {
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
}
