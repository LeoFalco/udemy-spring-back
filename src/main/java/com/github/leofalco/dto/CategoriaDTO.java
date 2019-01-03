package com.github.leofalco.dto;

import com.github.leofalco.interfaces.DataTransfer;
import com.github.leofalco.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements DataTransfer<CategoriaDTO, Categoria> {

    private static final long serialVersionUID = -8972499903386833214L;

    private Long id;
    private String descricao;

    @Override
    public CategoriaDTO asDTO() {
        return this;
    }

    @Override
    public Categoria asEntity() {
        return new Categoria(id, descricao);
    }


}
