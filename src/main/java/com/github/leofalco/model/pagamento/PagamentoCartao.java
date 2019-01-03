package com.github.leofalco.model.pagamento;

import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@Data
public class PagamentoCartao extends Pagamento {
    private static final long serialVersionUID = 20L;

    private Integer numeroDeParcelas;


    public PagamentoCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
        super(id, estado, pedido);
        this.numeroDeParcelas = numeroDeParcelas;
    }
}
