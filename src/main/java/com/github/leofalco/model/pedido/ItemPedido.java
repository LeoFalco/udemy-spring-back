package com.github.leofalco.model.pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.leofalco.model.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class ItemPedido implements Serializable {
    private static final long serialVersionUID = 1L;

    private ItemPedidoPK id = new ItemPedidoPK();

    private BigDecimal desconto;
    private Integer quantidade;
    private BigDecimal preco;


    public ItemPedido(Pedido pedido, Produto produto, BigDecimal desconto, Integer quantidade, BigDecimal preco) {
        super();
        id.setPedido(pedido);
        id.setProduto(produto);
        this.desconto = desconto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    @JsonIgnore
    @Transient
    public Pedido getPedido() {
        return id.getPedido();
    }

    @Transient
    public Produto getProduto() {
        return id.getProduto();
    }

    @EmbeddedId
    @JsonIgnore
    public ItemPedidoPK getId() {
        return id;
    }

    public void setId(ItemPedidoPK id) {
        this.id = id;
    }
}
