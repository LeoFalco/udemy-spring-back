package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.model.enumerador.EstadoPagamento;
import com.nelioalves.cursomc.model.enumerador.TipoCliente;

import javax.persistence.Entity;


@Entity
public class PagamentoCartao extends Pagamento {
    private static final long serialversionUID = 20L;

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
