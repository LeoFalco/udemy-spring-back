package com.github.leofalco.model;

import com.github.leofalco.Entidade;
import com.github.leofalco.dto.CategoriaDTO;
import com.github.leofalco.interfaces.AsDTO;
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
public class Categoria implements AsDTO<CategoriaDTO>, Entidade<Long> {

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


    @Override
    public CategoriaDTO asDTO() {
        return new CategoriaDTO(id, descricao);
    }

}
