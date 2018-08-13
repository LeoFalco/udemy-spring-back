package com.nelioalves.cursomc.service;

import com.nelioalves.cursomc.exeptions.OperationNotSupertedYetException;
import com.nelioalves.cursomc.interfaces.CrudServiceInteface;
import com.nelioalves.cursomc.model.Pedido;
import com.nelioalves.cursomc.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements CrudServiceInteface<Pedido, Integer> {

    private final PedidoRepository repo;

    @Autowired
    public PedidoService(PedidoRepository repo) {
        this.repo = repo;
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        return repo.save(pedido);
    }

    @Override
    public List<Pedido> listar() {
        return repo.findAll();
    }

    @Override
    public Pedido get(Integer integer) {
        Pedido one = repo.getOne(integer);
        one.toString();
        return one;
    }

    @Override
    public void remover(Integer id) {
        repo.delete(get(id));
    }

    @Override
    public Pedido atualizar(Pedido pedido) {
        throw new OperationNotSupertedYetException();
    }
}
