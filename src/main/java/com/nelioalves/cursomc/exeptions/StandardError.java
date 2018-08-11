package com.nelioalves.cursomc.exeptions;

import java.io.Serializable;

public class StantardError implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer status;
    private String errorName;
    private String message;
    private Long timestamp;

    public StantardError(Integer status, String errorName, String message, Long timestamp) {
        this.status = status;
        this.errorName = errorName;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
