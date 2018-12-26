package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.endereco.Estado;
import com.github.leofalco.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService implements CrudInterface<Estado, String> {

    final EstadoRepository repo;

    @Autowired
    public EstadoService(EstadoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Estado salvar(Estado estado) {
        return repo.save(estado);
    }

    @Override
    public List<Estado> listar() {
        return repo.findAll();
    }

    @Override
    public Estado get(String s) {
        Estado one = repo.getOne(s);
        one.toString();
        return one;
    }

    @Override
    public void remover(String id) {
        repo.delete(get(id));
    }

    @Override
    public Estado atualizar(Estado estado) {
        throw new OperationNotSupertedYetException();
    }
}
