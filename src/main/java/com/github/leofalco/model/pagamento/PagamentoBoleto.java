package com.github.leofalco.model.pagamento;

import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
