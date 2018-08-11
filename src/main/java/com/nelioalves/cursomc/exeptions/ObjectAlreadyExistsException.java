package com.nelioalves.cursomc.exeptions;

import java.io.Serializable;

public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(Class<?> tipo, Serializable id) {
        super("Objeto não encontrado! Id:" + id + ", Tipo: " + tipo.getName());
    }
}
