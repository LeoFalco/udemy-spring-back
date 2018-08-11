package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.model.Pedido;
import com.nelioalves.cursomc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository repo;

    public List<Pedido> listar(){
        return repo.findAll();
    }
}
