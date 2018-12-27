package com.github.leofalco.dto;

import com.github.leofalco.interfaces.AsEntity;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.enumerador.TipoCliente;

import java.util.List;

public class ClienteDTO implements AsEntity<Cliente> {

    Long id;
    String nome;
    TipoCliente tipoCliente;
    String email;
    String inscricaoFederal;
    List<EnderecoDTO> enderecos;
    List<String> telefones;

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
