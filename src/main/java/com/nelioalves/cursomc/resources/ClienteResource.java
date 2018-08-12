package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Cliente;
import com.nelioalves.cursomc.model.Pedido;
import com.nelioalves.cursomc.model.enumerador.EstadoPagamento;
import com.nelioalves.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
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
        return clienteService.listar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    private Cliente getOne(@PathVariable Integer id) {
        return clienteService.get(id);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}/pedidos")
    private List<Pedido> getPedidos(@PathVariable Integer id, @PathParam("estado") EstadoPagamento estado) {

        List<Pedido> pedidos = clienteService.get(id).getPedidos();

        if (estado != null) {
            pedidos = pedidos.stream()
                    .filter(pedido -> pedido.getPagamento().getEstado() == estado)
                    .collect(Collectors.toList());
        }
        return pedidos;
    }
}
