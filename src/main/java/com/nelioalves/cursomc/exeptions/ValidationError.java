package com.nelioalves.cursomc.exeptions;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ValidationError implements Serializable {
    private static final long serialVersionUID = 1L;

    private String error;
    private List<FieldMessage> messages = new ArrayList<>();
    private Integer status;
    private String path;

    @JsonFormat(pattern = "dd/MM/yyyy hh:mm:SS.ssss")
    private Long timestamp = System.currentTimeMillis();


    public ValidationError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<FieldMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<FieldMessage> messages) {
        this.messages = messages;
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

    public <T> void addError(String field, String message, T rejectedValue) {
        messages.add(new FieldMessage(field, message, rejectedValue));
    }
}
