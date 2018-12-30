package com.github.leofalco.model.enumerador;

public enum EstadoPagamento {

    PENDENTE("Pendente"),
    QUITADO("Quitado"),
    CANCELADO("Cancelado");

    private String descricao;

    EstadoPagamento(String descricao) {

        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
