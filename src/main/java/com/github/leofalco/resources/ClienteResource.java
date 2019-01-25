package com.github.leofalco.resources;

import com.github.leofalco.exeptions.custom.IdNotNullException;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.service.ClienteService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/clientes", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
@Api(tags = "Clientes")
@AllArgsConstructor(onConstructor_ = @Autowired)
public class ClienteResource {

    private final ClienteService clienteService;


    @GetMapping()
    private List<Cliente> listar() {
        return clienteService.listar();
    }

    @GetMapping(path = "/{id}")
    private Cliente obter(@PathVariable Integer id) {
        return clienteService.get(id);
    }

    @GetMapping(path = "/{id}/pedidos")
    private List<Pedido> listar(@PathVariable Integer id, @PathParam("estado") EstadoPagamento estado) {
        List<Pedido> pedidos = clienteService.get(id).getPedidos();

        List<Pedido> collect = pedidos.stream().peek(pedido -> pedido.setCliente(null)).collect(Collectors.toList());
        if (estado != null) {
            collect = collect.stream()
                    .filter(pedido -> pedido.getPagamento().getEstado() == estado)
                    .collect(Collectors.toList());
        }
        return collect;
    }

    @PostMapping
    private ResponseEntity<Cliente> inserir(@Valid @RequestBody Cliente cliente) {

        if (cliente.getId() != null)
            throw new IdNotNullException();

        cliente = clienteService.salvar(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }


    //atualiza cliente
    @PostMapping(path = "/{id}")
    private ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {

        cliente.setId(id);
        cliente = clienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

}
