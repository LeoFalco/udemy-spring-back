package com.github.leofalco.resources;

import com.github.leofalco.dto.CategoriaDTO;
import com.github.leofalco.exeptions.custom.IdNotNullExeption;
import com.github.leofalco.model.Categoria;
import com.github.leofalco.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    private final CategoriaService service;

    @Autowired
    public CategoriaResource(CategoriaService service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoriaDTO> listar() {
        // ignora produtos
        return service.listar()
                .stream()
                .map(Categoria::asDTO)
                .collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public CategoriaDTO get(@PathVariable("id") Long id) {
        return service.get(id).asDTO();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoriaDTO> post(@RequestBody Categoria categoria) {

        if (categoria.getId() != null) {
            throw new IdNotNullExeption();
        }

        categoria = service.salvar(categoria);

        String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri().toString();

        return ResponseEntity.status(HttpStatus.CREATED).header("location", uri).body(categoria.asDTO());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<CategoriaDTO> put(@RequestBody CategoriaDTO categoriaDTO, @PathVariable Long id) {

        Categoria categoria = categoriaDTO.asEntity();
        categoria.setId(id);

        categoria = service.atualizar(categoria);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoria.asDTO());
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.remover(id);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(defaultValue = "0", name = "pagina", required = false) Integer pagina,
            @RequestParam(defaultValue = "24", name = "linesPerPage", required = false) Integer linesPerPage,
            @RequestParam(defaultValue = "descricao", name = "orderBy", required = false) String orderBy,
            @RequestParam(defaultValue = "ASC", name = "direction", required = false) String direction) {

        return ResponseEntity.ok().body(service.findPage(pagina, linesPerPage, orderBy, direction).map(Categoria::asDTO));
    }

}
