package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.enumerator.model.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
public class PagamentoBoleto extends Pagamento {
    private static final long serialversionUID = 1L;

    private Calendar dataPagamento;
    private Calendar dataVencimento;

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
        this.dataVencimento = dataVendimento;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Temporal(TemporalType.DATE)
    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVendimento(Calendar dataVendimento) {
        this.dataVencimento = dataVendimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
