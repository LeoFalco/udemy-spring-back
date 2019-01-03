package com.github.leofalco.dto.errors;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String message;
    private HttpStatus status;
    private String path;
    private Object detalhe;
    private String method;

    //@JsonFormat(pattern = "dd/MM/yyyy hh:mm:SS.ssss")
    private final LocalDateTime timestamp = LocalDateTime.now();

}
