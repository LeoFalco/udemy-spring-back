package com.github.leofalco.model.endereco;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.leofalco.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estado implements PrimaryKey<String> {

    @Id
    @Column(length = 2, columnDefinition = "char(2)")
    private String uf;

    private String nome;

    @OneToMany(mappedBy = "estado")
    @JsonIgnore
    private List<Cidade> cidades = new ArrayList<>();

    @Override
    public String getId() {
        return uf;
    }

    public Estado(String uf, String nome) {
        this.uf = uf;
        this.nome = nome;
    }
}
