package com.nelioalves.cursomc.model.enumerador;

public enum TipoCliente {

    PESSOA_FISICA(1,"Pessoa Física"),
    PESSOA_JURIDICA(2,"Pessoa Juridica");

    private Integer id;
    private String descricao;


    TipoCliente(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
