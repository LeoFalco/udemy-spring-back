package com.nelioalves.cursomc.enumerator.model;

public enum EstadoPagamento {
    PENDENTE("Pendente"),
    QUITADO("Quitado"),
    CANCELADO("Cancelado");

    private final String descricao;

    EstadoPagamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
