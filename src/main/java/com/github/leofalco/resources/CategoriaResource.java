package com.github.leofalco.resources;

import com.github.leofalco.dto.CategoriaDTO;
import com.github.leofalco.exeptions.custom.IdNotNullException;
import com.github.leofalco.model.Categoria;
import com.github.leofalco.service.CategoriaService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/categorias",
        consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "Categorias")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoriaResource {

    private final CategoriaService service;
    private final ModelMapper modelMapper;


    @GetMapping(consumes = MediaType.ALL_VALUE)
    public List<CategoriaDTO> listar() {
        // ignora produtos
        return service.listarDTO();
    }


    @GetMapping(path = "/{id}")
    public Categoria obter(@PathVariable("id") Long id) {
        return service.get(id);
    }


    @PostMapping
    public ResponseEntity<CategoriaDTO> inserir(@RequestBody CategoriaDTO categoriaDTO) {

        if (categoriaDTO.getId() != null) {
            throw new IdNotNullException();
        }

        Categoria categoria = modelMapper.map(categoriaDTO, Categoria.class);

        categoria = service.salvar(categoria);

        modelMapper.map(categoria, categoriaDTO);

        String uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoria.getId())
                .toUri().toString();

        return ResponseEntity.status(HttpStatus.CREATED).header("location", uri).body(categoriaDTO);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CategoriaDTO> atualizar(@RequestBody CategoriaDTO categoria, @PathVariable Long id) {
        categoria.setId(id);

        Categoria map = modelMapper.map(categoria, Categoria.class);
        map = service.atualizar(map);
        modelMapper.map(map, categoria);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoria);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/page")
    public ResponseEntity<Page<Categoria>> pagina(
            @RequestParam(defaultValue = "0", name = "pagina", required = false) Integer pagina,
            @RequestParam(defaultValue = "24", name = "linesPerPage", required = false) Integer linesPerPage,
            @RequestParam(defaultValue = "descricao", name = "orderBy", required = false) String orderBy,
            @RequestParam(defaultValue = "ASC", name = "direction", required = false) String direction) {

        return ResponseEntity.ok().body(service.findPage(pagina, linesPerPage, orderBy, direction));
    }

}
