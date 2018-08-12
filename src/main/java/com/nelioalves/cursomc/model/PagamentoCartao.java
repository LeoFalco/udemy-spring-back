package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.enumerator.model.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;


@Entity
public class PagamentoCartao extends Pagamento {
    private static final long serialversionUID = 1L;

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
