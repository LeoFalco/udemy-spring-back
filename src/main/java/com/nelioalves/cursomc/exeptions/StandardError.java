package com.nelioalves.cursomc.exeptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


public class StandardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private String message;
    private Integer status;
    private String path;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:SS.ssss")
    private Long timestamp = System.currentTimeMillis();

    public StandardError(HttpStatus status, Exception ex, String path) {
        this.error = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
        this.status = status.value();
        this.path = path;
    }

    public StandardError(HttpStatus status, Exception ex, String path, String message) {
        this.error = ex.getClass().getSimpleName();
        this.message = message;
        this.status = status.value();
        this.path = path;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "StandardError{" +
                "error='" + error + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", path='" + path + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
