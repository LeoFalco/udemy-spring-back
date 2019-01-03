package com.github.leofalco.resources;

import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.service.PedidoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.leofalco.util.Constants.APPLICATION_JSON_CHARSET_UTF8;

@RestController
@RequestMapping(value = "/pedidos", consumes = APPLICATION_JSON_CHARSET_UTF8, produces = APPLICATION_JSON_CHARSET_UTF8)
@Api(description = "Manipulação de pedidos", tags = "Pedidos")
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
        return pedidoService.get(id);
    }


}
