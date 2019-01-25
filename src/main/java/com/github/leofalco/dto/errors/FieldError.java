package com.github.leofalco.dto.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class FieldError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String campo;
    private String message;
    private transient Object rejectedValue;
}
