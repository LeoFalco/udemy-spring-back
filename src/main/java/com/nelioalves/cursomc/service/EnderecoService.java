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
