package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.exeptions.ObjectAlreadyExistsException;
import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private static final Logger logger = Logger.getLogger(CategoriaResource.class.getName());

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
    public Categoria get(@PathVariable("id") Integer id) {

        Categoria categoria = service.get(id);

        categoria.toString();

        return categoria;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Categoria post(@RequestBody Categoria categoria) {

        try {
            Categoria serviceOne = service.get(categoria.getId());

            throw new ObjectAlreadyExistsException(Categoria.class, categoria.getId());
        } catch (EntityNotFoundException ignore) {
        }


        categoria = service.salvar(categoria);

        return categoria;
    }
}
