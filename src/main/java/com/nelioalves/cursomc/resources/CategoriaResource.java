package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.dto.CategoriaDto;
import com.nelioalves.cursomc.model.Categoria;
import com.nelioalves.cursomc.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    public List<CategoriaDto> listar() {
        return service.listar().stream()
                .map(CategoriaDto::new)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Categoria get(@PathVariable("id") Integer id) {

        Categoria categoria = service.get(id);


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

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Categoria> put(@RequestBody Categoria categoria, @PathVariable Integer id) {

        categoria.setId(id);

        categoria = service.atualizar(categoria);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoria);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }


}
