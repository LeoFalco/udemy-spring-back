package com.github.leofalco.resources;

import com.github.leofalco.dto.ClienteDTO;
import com.github.leofalco.exeptions.custom.IdNotNullExeption;
import com.github.leofalco.model.Cliente;
import com.github.leofalco.model.enumerador.EstadoPagamento;
import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    private final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @RequestMapping(method = RequestMethod.GET)
    private List<ClienteDTO> listar() {
        List<Cliente> clientes = clienteService.listar();
        return clientes.stream().map(Cliente::asDTO).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    private Cliente getOne(@PathVariable Integer id) {
        return clienteService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/pedidos")
    private List<Pedido> getPedidos(@PathVariable Integer id, @PathParam("estado") EstadoPagamento estado) {

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
    private ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {

        if (cliente.getId() != null)
            throw new IdNotNullExeption();

        cliente = clienteService.salvar(cliente);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).body(cliente);
    }


    //atualiza cliente
    @RequestMapping(method = RequestMethod.POST, path = "/{id}")
    private ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {

        cliente.setId(id);
        cliente = clienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

}