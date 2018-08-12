package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.enumerator.model.EstadoPagamento;

import java.util.Calendar;

public class PagamentoBoleto extends Pagamento {
    private static final long serialversionUID = 1L;

    private Calendar dataPagamento;
    private Calendar dataVendimento;

    public PagamentoBoleto() {
        super();
    }

    public PagamentoBoleto(Integer id,
                           EstadoPagamento estado,
                           Pedido pedido,
                           Calendar dataPagamento,
                           Calendar dataVendimento) {
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVendimento = dataVendimento;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Calendar getDataVendimento() {
        return dataVendimento;
    }

    public void setDataVendimento(Calendar dataVendimento) {
        this.dataVendimento = dataVendimento;
    }
}
