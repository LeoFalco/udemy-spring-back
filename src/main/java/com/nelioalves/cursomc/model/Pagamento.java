package com.nelioalves.cursomc.model;

import com.nelioalves.cursomc.enumerator.model.EstadoPagamento;

import java.io.Serializable;
import java.util.Objects;

public abstract class Pagamento implements Serializable {
    private static final long serialversionUID = 1L;

    private Integer id;
    private EstadoPagamento estado;
    private Pedido pedido;

    Pagamento() {
    }

    Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado;
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
