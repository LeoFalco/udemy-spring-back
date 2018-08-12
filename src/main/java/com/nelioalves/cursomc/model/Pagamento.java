package com.nelioalves.cursomc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nelioalves.cursomc.model.enumerador.EstadoPagamento;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
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

    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public EstadoPagamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado;
    }


    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    @MapsId
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        pedido.setPagamento(this);
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

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", estado=" + estado +
                ", pedido=" + pedido.getId() +
                '}';
    }
}
