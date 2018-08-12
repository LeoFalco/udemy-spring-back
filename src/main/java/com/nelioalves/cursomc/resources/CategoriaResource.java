package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.exeptions.ObjectAlreadyExistsException;
import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    final
    CategoriaService service;

    @Autowired
    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {
        return service.listar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Categoria getOne(@PathVariable("id") Integer id) {

        Categoria categoria = service.get(id);

        System.out.println(categoria);
        return categoria;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Categoria postOne(@RequestBody Categoria categoria) {

        boolean existe = false;
        try {
            Categoria serviceOne = service.get(categoria.getId());

            throw new ObjectAlreadyExistsException(Categoria.class, categoria.getId());
        } catch (EntityNotFoundException ignore) {
        }


        categoria = service.salvar(categoria);

        return categoria;
    }

}
