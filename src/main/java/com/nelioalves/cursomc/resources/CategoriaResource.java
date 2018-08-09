package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @Autowired
    CategoriaService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {
        return service.listar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Categoria getOne(@PathVariable("id") Integer id) {

        Categoria categoria = service.getOne(id);
        System.out.println("id: " + categoria.getId());
        System.out.println("nome: " + categoria.getNome());

        return categoria;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Categoria getOne(@RequestBody Categoria categoria) {

        /*
        service.getOne(categoria.getId());
        System.out.println(categoria.getId());
        System.out.println(categoria.getNome());
        */

        categoria = service.save(categoria);


        return categoria;
    }

}
