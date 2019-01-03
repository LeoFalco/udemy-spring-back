package com.github.leofalco.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, length = 50)
    private String descricao;

    @ManyToMany(mappedBy = "categorias", fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<>();


    public Categoria(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
