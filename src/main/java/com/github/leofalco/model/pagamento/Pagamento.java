package com.github.leofalco.model.pagamento;

import column.Def;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = Def.com_github_leofalco_model_enumerador_EstadoPagamento)
    private EstadoPagamento estado;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;
}
