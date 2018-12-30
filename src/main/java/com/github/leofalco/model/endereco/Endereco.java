package com.github.leofalco.model.endereco;

import com.github.leofalco.PrimaryKey;
import com.github.leofalco.model.Cliente;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco implements PrimaryKey<Long> {
    

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    @ManyToOne
    private Cidade cidade;
    @ManyToOne
    private Cliente cliente;

}
