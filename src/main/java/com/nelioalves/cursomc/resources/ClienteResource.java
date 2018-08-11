package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoResource {

    @Autowired
    PedidoService pedidoService
}
