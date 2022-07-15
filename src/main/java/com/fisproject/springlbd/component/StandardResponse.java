package com.fisproject.springlbd.component;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class StandardResponse {

    private Integer status;
    private Object data;
    private String message;

    public StandardResponse(HttpStatus status, Object data, String message) {
        this.status=status.value();
        this.data=data;
        this.message=message;
    }

    public Integer getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
