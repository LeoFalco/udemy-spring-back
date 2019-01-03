package com.github.leofalco.model.endereco;

import com.github.leofalco.PrimaryKey;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cidade")
@Data
public class Cidade implements PrimaryKey<Long> {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;
}
