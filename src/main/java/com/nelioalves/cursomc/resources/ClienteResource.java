package com.nelioalves.cursomc.resources;

import com.nelioalves.cursomc.model.Cliente;
import com.nelioalves.cursomc.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    ClienteService clienteService;


    @RequestMapping(method = RequestMethod.GET)
    private List<Cliente> listar() {
        return clienteService.listar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    private Cliente getOne(@PathVariable Integer id) {
        return clienteService.get(id);
    }

}
