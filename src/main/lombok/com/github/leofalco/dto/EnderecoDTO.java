package com.github.leofalco.dto;

import com.github.leofalco.interfaces.AsEntity;
import com.github.leofalco.model.endereco.Cidade;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.model.endereco.Estado;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EnderecoDTO implements AsEntity<Endereco> {

    private Long id;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;
    private Cidade cidadeNome;
    private String estadoSigla;
    private String estadoNome;

    @Override
    public Endereco asEntity() {
        Endereco endereco = new Endereco();

        endereco.setId(id);
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCep(cep);

        Cidade cidade = new Cidade();
        cidade.setId(cidadeId);
        endereco.setCidade(cidade);

        Estado estado = new Estado();
        estado.setUf(estadoSigla);
        estado.setNome(estadoNome);

        cidade.setEstado(estado);

        return endereco;
    }
}
