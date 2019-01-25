package com.github.leofalco.service;

import com.github.leofalco.dto.CategoriaDTO;
import com.github.leofalco.exeptions.custom.RelacionamentoException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.Categoria;
import com.github.leofalco.repository.CategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoriaService implements CrudInterface<Categoria, Long> {

    private final CategoriaRepository repo;
    private final EntityManager em;

    @Override
    public Categoria salvar(Categoria categoria) {
        return repo.save(categoria);
    }

    @Override
    public List<Categoria> listar() {
        return repo.findAll();
    }


    public List<CategoriaDTO> listarDTO() {
        return repo.findAll().stream().map(categoria -> {
            return new CategoriaDTO(categoria.getId(), categoria.getDescricao());
        }).collect(Collectors.toList());
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
            throw new RelacionamentoException(ex);
        }
    }

    @Override
    public Categoria atualizar(Categoria categoria) {
        return repo.save(categoria);
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        return repo.findAll(
                PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy));
    }

}
