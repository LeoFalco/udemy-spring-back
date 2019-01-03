package com.github.leofalco.model.pagamento;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

import static column.Def.com_github_leofalco_model_enumerador_EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = com_github_leofalco_model_enumerador_EstadoPagamento)
    private EstadoPagamento estado;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;
}
