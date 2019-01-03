package com.github.leofalco.resources;

import com.github.leofalco.exeptions.custom.IdNotNullException;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.service.ClienteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.leofalco.util.Constants.APPLICATION_JSON_CHARSET_UTF8;

@RestController
@RequestMapping(value = "/clientes", consumes = APPLICATION_JSON_CHARSET_UTF8, produces = APPLICATION_JSON_CHARSET_UTF8)
@Api(description = "Manipulação de Clientes", tags = "Clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @RequestMapping(method = RequestMethod.GET)
    private List<Cliente> listar() {
        return clienteService.listar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    private Cliente obter(@PathVariable Integer id) {
        return clienteService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/pedidos")
    private List<Pedido> listar(@PathVariable Integer id, @PathParam("estado") EstadoPagamento estado) {

        System.out.println("/pedidos");
        List<Pedido> pedidos = clienteService.get(id).getPedidos();

        List<Pedido> collect = pedidos.stream().peek(pedido -> pedido.setCliente(null)).collect(Collectors.toList());
        if (estado != null) {
            collect = collect.stream()
                    .filter(pedido -> pedido.getPagamento().getEstado() == estado)
                    .collect(Collectors.toList());
        }
        return collect;
    }

    //cria cliente
    @RequestMapping(method = RequestMethod.POST)
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
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    private ResponseEntity<Cliente> atualizar(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {

        cliente.setId(id);
        cliente = clienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

}
