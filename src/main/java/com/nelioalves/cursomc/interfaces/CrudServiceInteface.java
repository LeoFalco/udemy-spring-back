package com.nelioalves.cursomc.interfaces;

import java.util.List;

public interface CrudServiceInteface<T, I> {

    public T salvar();

    public List<T> listar();

    public T get(I i);

    public void remover(T t);

    public void atualizar(T t);

}
