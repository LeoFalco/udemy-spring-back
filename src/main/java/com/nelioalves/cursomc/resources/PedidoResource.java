package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Pedido;
import com.nelioalves.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
public class PedidoResource {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoResource(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Pedido> listar() {

        return pedidoService.listar().stream().peek(pedido -> {
            pedido.getCliente().setEnderecos(null);
        }).collect(Collectors.toList());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public Pedido get(@PathVariable Integer id) {


        Pedido pedido = pedidoService.get(id);
        pedido.toString();
        return pedido;
    }


}
