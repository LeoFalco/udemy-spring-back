package com.nelioalves.cursomc.exeptions;

public class OperationNotSupertedYetException extends RuntimeException {
    private static final long serialVersionUID = 1L;


    public OperationNotSupertedYetException() {
        super("Operação ainda não implementada");
    }

    public OperationNotSupertedYetException(String message) {
        super(message);
    }

    public OperationNotSupertedYetException(String message, Throwable cause) {
        super(message, cause);
    }

    public OperationNotSupertedYetException(Throwable cause) {
        super(cause);
    }

    public OperationNotSupertedYetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
