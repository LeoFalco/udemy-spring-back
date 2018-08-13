package com.nelioalves.cursomc.interfaces;

import java.util.List;

public interface CrudServiceInteface<T, I> {

    T salvar(T t);

    List<T> listar();

    T get(I i);

    void remover(I i);

    T atualizar(T t);

}
