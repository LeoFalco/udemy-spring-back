package com.github.leofalco.resources;

import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.service.PedidoService;
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
    public Pedido obter(@PathVariable Integer id) {


        Pedido pedido = pedidoService.get(id);
        return pedido;
    }


}
