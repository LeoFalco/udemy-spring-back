package com.nelioalves.cursomc.exeptions;

public class IdNotNullExeption extends RuntimeException {

    public IdNotNullExeption() {
        super("Id nulo requerido para esta operação");
    }
}
