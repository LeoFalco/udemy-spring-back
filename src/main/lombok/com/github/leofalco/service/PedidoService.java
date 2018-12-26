package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.pedido.Pedido;
import com.github.leofalco.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements CrudInterface<Pedido, Integer> {

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
