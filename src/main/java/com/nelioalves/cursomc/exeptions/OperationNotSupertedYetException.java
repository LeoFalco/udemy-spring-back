package com.nelioalves.cursomc.exeptions;

public class OperationNotSupertedYetException extends RuntimeException {

    public OperationNotSupertedYetException() {
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
