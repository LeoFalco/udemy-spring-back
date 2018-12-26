package com.github.leofalco.dto.errors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorDTO<O> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String message;
    private HttpStatus status;
    private String path;
    private O detalhe;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:SS.ssss")
    private final LocalDateTime timestamp = LocalDateTime.now();

}
