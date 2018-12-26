package com.github.leofalco.model.enumerador;

public enum TipoCliente {
    PESSOA_FISICA("Pessoa Física"),
    PESSOA_JURIDICA("Pessoa Jurídica");

    private String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }


    public String getDescricao() {
        return descricao;
    }

}
