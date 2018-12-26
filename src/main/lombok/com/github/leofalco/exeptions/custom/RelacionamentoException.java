package com.github.leofalco.exeptions.custom;

public class RelacionamentoException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public RelacionamentoException() {
        this("O objeto está sendo usado em um relacionamento");
    }

    public RelacionamentoException(String message) {
        super(message);
    }

    public RelacionamentoException(String message, Throwable cause) {
        super(message, cause);
    }

    public RelacionamentoException(Throwable cause) {
        super(cause);
    }

    public RelacionamentoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
