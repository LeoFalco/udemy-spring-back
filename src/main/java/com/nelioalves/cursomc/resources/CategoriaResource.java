package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private static final Logger logger = Logger.getLogger(CategoriaResource.class.getName());

    final CategoriaService service;

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
    public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
        categoria = service.salvar(categoria);
        String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri().toString();


        return ResponseEntity.status(HttpStatus.CREATED).header("location", uri).body(categoria);
    }
}
