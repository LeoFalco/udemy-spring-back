package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.RelacionamentoException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.Categoria;
import com.github.leofalco.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService implements CrudInterface<Categoria, Long> {

    private final CategoriaRepository repo;

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
    public Categoria get(Long integer) {
        return repo.getOne(integer);
    }

    @Override
    public void remover(Long id) {
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

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return repo.findAll(
                PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

}
