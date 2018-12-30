package com.github.leofalco.dto;

import com.github.leofalco.interfaces.AsEntity;
import com.github.leofalco.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaDTO implements AsEntity<Categoria> {

    private static final long serialVersionUID = -8972499903386833214L;
    private Long id;
    private String descricao;

    @Override
    public Categoria asEntity() {
        return new Categoria(id, descricao);
    }
}
