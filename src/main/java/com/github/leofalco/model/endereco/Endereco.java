package com.github.leofalco.model.endereco;

import com.github.leofalco.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cidade cidade;
    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
}
