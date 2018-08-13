package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.exeptions.RelacionamentoException;
import com.nelioalves.cursomc.interfaces.CrudServiceInteface;
import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
        Categoria one = repo.getOne(integer);
        one.toString();
        return one;
    }

    @Override
    public void remover(Integer id) {
        Categoria categoria = get(id);
        try {
            repo.delete(categoria);
        } catch (DataIntegrityViolationException ex) {
            throw new RelacionamentoException();
        }
    }

    @Override
    public Categoria atualizar(Categoria categoria) {
        Categoria existe = get(categoria.getId());

        existe.toString();

        return repo.save(categoria);
    }

}
