package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.exeptions.OperationNotSupertedYetException;
import com.nelioalves.cursomc.interfaces.CrudServiceInteface;
import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements CrudServiceInteface<Categoria, Integer> {

    final CategoriaRepository repo;

    @Autowired
    public CategoriaService(CategoriaRepository repo) {
        this.repo = repo;
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return repo.save(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return repo.findAll();
    }

    @Override
    public Categoria get(Integer integer) {
        return repo.getOne(integer);
    }

    @Override
    public void remover(Categoria categoria) {
        throw new OperationNotSupertedYetException();
    }

    @Override
    public void atualizar(Categoria categoria) {
        throw new OperationNotSupertedYetException();
    }

}
