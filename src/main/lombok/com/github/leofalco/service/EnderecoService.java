package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService implements CrudInterface<Endereco, Integer> {

    final
    EnderecoRepository repo;

    @Autowired
    public EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        Endereco save = repo.save(endereco);
        return save;
    }

    @Override
    public List<Endereco> listar() {
        return repo.findAll();
    }

    @Override
    public Endereco get(Integer integer) {
        Endereco one = repo.getOne(integer);
        one.toString();
        return one;
    }

    @Override
    public void remover(Integer id) {
        repo.delete(get(id));
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        throw new OperationNotSupertedYetException();
    }
}
