package com.github.leofalco.exeptions;

import java.io.Serializable;

public class FieldMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campo;
    private String message;
    private T rejectedValue;

    public FieldMessage(String campo, String message, T rejectedValue) {
        this.campo = campo;
        this.message = message;
        this.rejectedValue = rejectedValue;
    }

    public FieldMessage() {
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(T rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
}
