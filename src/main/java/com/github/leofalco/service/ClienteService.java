package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ClienteService implements CrudInterface<Cliente, Integer> {

    private final ClienteRepository repo;

    @Override
    public Cliente salvar(Cliente cliente) {
        return repo.save(cliente);
    }

    @Override
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @Override
    public Cliente get(Integer integer) {
        return repo.getOne(integer);
    }

    @Override
    public void remover(Integer id) {
        throw new OperationNotSupertedYetException();
    }

    @Override
    public Cliente atualizar(Cliente cliente) {
        throw new OperationNotSupertedYetException();
    }
}
