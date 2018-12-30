package com.github.leofalco.exeptions.custom;

public class IdNotNullExeption extends RuntimeException {

    private static final long serialVersionUID = 9145047925463136116L;

    public IdNotNullExeption() {
        super("PrimaryKey nulo requerido para esta operação");
    }
}
