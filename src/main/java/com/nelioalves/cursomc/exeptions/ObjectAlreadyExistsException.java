package com.nelioalves.cursomc.exeptions;

import java.io.Serializable;

public class ObjectAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public ObjectAlreadyExistsException(String message) {
        super(message);
    }

    public ObjectAlreadyExistsException(Class<?> tipo, Serializable id) {
        super("Objeto já existe! Id:" + id + ", Tipo: " + tipo.getName());
    }
}
