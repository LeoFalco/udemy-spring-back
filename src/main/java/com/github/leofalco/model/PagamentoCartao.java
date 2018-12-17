package com.github.leofalco.model;

import com.github.leofalco.model.enumerador.EstadoPagamento;

import javax.persistence.Entity;


@Entity
public class PagamentoCartao extends Pagamento {
    private static final long serialVersionUID = 20L;

    private Integer numeroDeParcelas;

    public PagamentoCartao() {
        super();
    }


    public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }

    public Integer getNumeroDeParcelas() {
        return numeroDeParcelas;
    }

    public void setNumeroDeParcelas(Integer numeroDeParcelas) {
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
