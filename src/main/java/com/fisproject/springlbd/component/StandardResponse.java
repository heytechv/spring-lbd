package com.fisproject.springlbd.component;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

public class StandardResponse {

    private HttpStatus status;
    private Object data;
    private String message;

    public StandardResponse(HttpStatus status, Object data, String message) {
        this.status=status;
        this.data=data;
        this.message=message;
    }

    public HttpStatus getStatus() { return status; }
    public void setStatus(HttpStatus status) { this.status = status; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
