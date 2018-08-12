package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.exeptions.OperationNotSupertedYetException;
import com.nelioalves.cursomc.interfaces.CrudServiceInteface;
import com.nelioalves.cursomc.model.Endereco;
import com.nelioalves.cursomc.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService implements CrudServiceInteface<Endereco, Integer> {

    final
    EnderecoRepository repo;

    @Autowired
    public EnderecoService(EnderecoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Endereco salvar(Endereco endereco) {
        return repo.save(endereco);
    }

    @Override
    public List<Endereco> listar() {
        return repo.findAll();
    }

    @Override
    public Endereco get(Integer integer) {
        return repo.getOne(integer);
    }

    @Override
    public void remover(Endereco endereco) {
        repo.delete(endereco);
    }

    @Override
    public void atualizar(Endereco endereco) {
        throw new OperationNotSupertedYetException();
    }
}
