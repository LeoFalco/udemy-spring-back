package com.github.leofalco.exeptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FieldErrorMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campo;
    private String message;
    private Object rejectedValue;
}
