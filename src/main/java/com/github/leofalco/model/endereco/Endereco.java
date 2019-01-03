package com.github.leofalco.model.endereco;

import com.github.leofalco.PrimaryKey;
import com.github.leofalco.dto.EnderecoDTO;
import com.github.leofalco.interfaces.DataTransfer;
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
public class Endereco implements DataTransfer<EnderecoDTO, Endereco>, PrimaryKey<Long> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    @ManyToOne
    private Cidade cidade;
    @ManyToOne
    private Cliente cliente;

    @Override
    public EnderecoDTO asDTO() {
        return EnderecoDTO.builder()
                .id(id)
                .logradouro(logradouro)
                .numero(numero)
                .complemento(complemento)
                .bairro(bairro)
                .cep(cep)
                .cidadeId(cidade.getId())
                .cidadeNome(cidade.getNome())
                .build();
    }

    @Override
    public Endereco asEntity() {
        return this;
    }
}
