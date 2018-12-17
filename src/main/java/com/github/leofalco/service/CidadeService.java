package com.github.leofalco.service;

import com.github.leofalco.exeptions.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudServiceInteface;
import com.github.leofalco.model.Cidade;
import com.github.leofalco.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService implements CrudServiceInteface<Cidade, Integer> {

    private final CidadeRepository repo;

    @Autowired
    public CidadeService(CidadeRepository repo) {
        this.repo = repo;
    }


    @Override
    public Cidade salvar(Cidade cidade) {
        return repo.save(cidade);
    }

    @Override
    public List<Cidade> listar() {
        return repo.findAll();
    }

    @Override
    public Cidade get(Integer integer) {
        Cidade one = repo.getOne(integer);
        one.toString();
        return one;
    }

    @Override
    public void remover(Integer id) {
        repo.delete(get(id));
    }

    @Override
    public Cidade atualizar(Cidade cidade) {
        throw new OperationNotSupertedYetException();
    }
}
