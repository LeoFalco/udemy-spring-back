package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Cliente;
import com.nelioalves.cursomc.model.Pedido;
import com.nelioalves.cursomc.model.enumerador.EstadoPagamento;
import com.nelioalves.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    final ClienteService clienteService;

    @Autowired
    public ClienteResource(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @RequestMapping(method = RequestMethod.GET)
    private List<Cliente> listar() {


        List<Cliente> clientes = clienteService.listar();

        List<Cliente> collect = clientes.stream().peek(cliente -> {
            cliente.setEnderecos(null);
            cliente.setTelefones(null);
        }).collect(Collectors.toList());

        return collect;
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

    //post
    //cria ou atualiza cliente
    @RequestMapping(method = RequestMethod.POST)
    private ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {

        Cliente clienteManaged = clienteService.salvar(cliente);

        if (cliente.getId() == null) { // cria
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(clienteManaged.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(clienteManaged);
        } else { // atualiza
            return ResponseEntity.ok(clienteManaged);
        }
    }

}
