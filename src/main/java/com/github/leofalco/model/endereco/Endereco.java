package com.github.leofalco.model.endereco;

import com.github.leofalco.Entidade;
import com.github.leofalco.model.Cliente;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Endereco implements Entidade<Long> {
    private static final long serialVersionUID = 1L;

    @Id
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
