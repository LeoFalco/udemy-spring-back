package com.github.leofalco.service;

import com.github.leofalco.exeptions.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudServiceInteface;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements CrudServiceInteface<Cliente, Integer> {

    final ClienteRepository repo;

    @Autowired
    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

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
        Cliente one = repo.getOne(integer);
        one.toString();
        return one;
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
