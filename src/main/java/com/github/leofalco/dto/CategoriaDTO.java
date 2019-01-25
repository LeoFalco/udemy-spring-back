package com.github.leofalco.dto;

import com.mysema.query.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(onConstructor_ = @QueryProjection)
public class CategoriaDTO {
    private Long id;
    private String descricao;
}
