package com.github.leofalco.service;

import com.github.leofalco.exeptions.custom.OperationNotSupertedYetException;
import com.github.leofalco.interfaces.CrudInterface;
import com.github.leofalco.model.endereco.Endereco;
import com.github.leofalco.repository.EnderecoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class EnderecoService implements CrudInterface<Endereco, Integer> {

    private final EnderecoRepository repo;

    @Override
    public Endereco salvar(Endereco endereco) {
        return repo.save(endereco);
    }

    @Override
    public List<Endereco> listar() {
        return repo.findAll();
    }

    @Override
    public Endereco get(Integer integer) {
        Endereco one = repo.getOne(integer);
        one.toString();
        return one;
    }

    @Override
    public void remover(Integer id) {
        repo.delete(get(id));
    }

    @Override
    public Endereco atualizar(Endereco endereco) {
        throw new OperationNotSupertedYetException();
    }
}
