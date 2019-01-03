package com.github.leofalco.model.pagamento;

import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class PagamentoBoleto extends Pagamento {
    private static final long serialVersionUID = 1L;

    private LocalDateTime dataPagamento;
    private LocalDate dataVencimento;

    public PagamentoBoleto(Integer id,
                           EstadoPagamento estado,
                           Pedido pedido,
                           LocalDateTime dataPagamento,
                           LocalDate dataVencimento) {
        super(id, estado, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }

}
