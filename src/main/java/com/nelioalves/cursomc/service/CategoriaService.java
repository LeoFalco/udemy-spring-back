package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRepository repo;

    public Categoria getOne(Integer id) {
        return repo.getOne(id);
    }

    public List<Categoria> listar() {
        return repo.findAll();
    }

    public Categoria save(Categoria categoria) {
        return repo.save(categoria);
    }
}
