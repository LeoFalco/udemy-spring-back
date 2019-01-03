package com.github.leofalco.exeptions.custom;

public class IdNotNullException extends RuntimeException {

    private static final long serialVersionUID = 9145047925463136116L;

    public IdNotNullException() {
        super("Id nulo requerido para esta operação");
    }
}
