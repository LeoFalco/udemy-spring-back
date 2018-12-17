package com.github.leofalco.model;

import com.github.leofalco.model.enumerador.EstadoPagamento;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class PagamentoBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private Date dataPagamento;
    private Date dataVencimento;

    public PagamentoBoleto() {
        super();
    }

    public PagamentoBoleto(Integer id,
                           EstadoPagamento estado,
                           Pedido pedido,
                           Date dataPagamento,
                           Date dataVendimento) {
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVendimento;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    @Temporal(TemporalType.DATE)
    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVendimento(Date dataVendimento) {
        this.dataVencimento = dataVendimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
}
