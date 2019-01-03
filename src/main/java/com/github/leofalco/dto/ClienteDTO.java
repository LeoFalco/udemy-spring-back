package com.github.leofalco.dto;

import com.github.leofalco.interfaces.DataTransfer;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.enumerador.TipoCliente;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClienteDTO implements DataTransfer<ClienteDTO, Cliente> {

    private static final long serialVersionUID = 5735300954899617083L;
    private Long id;
    private String nome;
    private TipoCliente tipoCliente;
    private String email;
    private String inscricaoFederal;
    private List<EnderecoDTO> enderecos = new ArrayList<>();
    private List<String> telefones = new ArrayList<>();

    @Override
    public ClienteDTO asDTO() {
        return this;
    }

    @Override
    public Cliente asEntity() {
        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setTipo(tipoCliente);
        cliente.setEmail(email);
        cliente.setInscricaoFederal(inscricaoFederal);
        cliente.getTelefones().addAll(telefones);
        return cliente;
    }
}
