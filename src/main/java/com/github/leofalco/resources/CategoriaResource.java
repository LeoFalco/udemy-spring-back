package com.github.leofalco.resources;

import com.github.leofalco.exeptions.custom.IdNotNullException;
import com.github.leofalco.model.Categoria;
import com.github.leofalco.service.CategoriaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static com.github.leofalco.util.Constants.APPLICATION_JSON_CHARSET_UTF8;

@RestController
@RequestMapping(value = "/categorias", consumes = APPLICATION_JSON_CHARSET_UTF8, produces = APPLICATION_JSON_CHARSET_UTF8)
@Api(description = "Manipulação de categorias", tags = "Categorias")
public class CategoriaResource {

    private final CategoriaService service;

    @Autowired
    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar() {
        // ignora produtos
        return service.listar();
    }


    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Categoria obter(@PathVariable("id") Long id) {
        return service.get(id);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Categoria> inserir(@RequestBody Categoria categoria) {

        if (categoria.getId() != null) {
            throw new IdNotNullException();
        }

        categoria = service.salvar(categoria);

        String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri().toString();

        return ResponseEntity.status(HttpStatus.CREATED).header("location", uri).body(categoria);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Categoria> atualizar(@RequestBody Categoria categoria, @PathVariable Long id) {
        categoria.setId(id);
        categoria = service.atualizar(categoria);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoria);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/page")
    public ResponseEntity<Page<Categoria>> pagina(
            @RequestParam(defaultValue = "0", name = "pagina", required = false) Integer pagina,
            @RequestParam(defaultValue = "24", name = "linesPerPage", required = false) Integer linesPerPage,
            @RequestParam(defaultValue = "descricao", name = "orderBy", required = false) String orderBy,
            @RequestParam(defaultValue = "ASC", name = "direction", required = false) String direction) {

        return ResponseEntity.ok().body(service.findPage(pagina, linesPerPage, orderBy, direction));
    }

}
