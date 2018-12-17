package com.github.leofalco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estado")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    private String uf;
    private String nome;
    private List<Cidade> cidades = new ArrayList<>();


    public Estado() {
    }

    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }

    @Id
    @Column(length = 2, columnDefinition = "char(2)")
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy = "estado")
    @JsonIgnore
    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estado)) return false;
        Estado estado = (Estado) o;
        return Objects.equals(uf, estado.uf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uf);
    }
}
