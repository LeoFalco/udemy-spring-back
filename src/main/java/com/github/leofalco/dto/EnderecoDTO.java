package com.github.leofalco.dto;

import com.github.leofalco.interfaces.DataTransfer;
import com.github.leofalco.model.endereco.Cidade;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.model.endereco.Estado;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EnderecoDTO implements DataTransfer<EnderecoDTO, Endereco> {

    private static final long serialVersionUID = -4735676142839964067L;
    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Long cidadeId;
    private String cidadeNome;
    private String estadoSigla;
    private String estadoNome;

    @Override
    public EnderecoDTO asDTO() {
        return this;
    }

    @Override
    public Endereco asEntity() {
        Endereco endereco = new Endereco();

        endereco.setId(id);
        endereco.setLogradouro(logradouro);
        endereco.setComplemento(complemento);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCep(cep);

        Cidade cidade = new Cidade();
        cidade.setId(cidadeId);
        cidade.setNome(cidadeNome);
        endereco.setCidade(cidade);

        Estado estado = new Estado();
        estado.setUf(estadoSigla);
        estado.setNome(estadoNome);

        cidade.setEstado(estado);

        return endereco;
    }
}
