package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.exeptions.OperationNotSupertedYetException;
import com.nelioalves.cursomc.interfaces.CrudServiceInteface;
import com.nelioalves.cursomc.model.Estado;
import com.nelioalves.cursomc.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService implements CrudServiceInteface<Estado, String> {

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
        return repo.getOne(s);
    }

    @Override
    public void remover(Estado estado) {
        repo.delete(estado);
    }

    @Override
    public Estado atualizar(Estado estado) {
        throw new OperationNotSupertedYetException();
    }
}
