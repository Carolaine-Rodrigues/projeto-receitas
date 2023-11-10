package com.receitas.infra.exeptions;

import org.springframework.http.HttpStatus;

public class ExeptionDTO{

    private HttpStatus httpStatus;
    private String message;

    public ExeptionDTO(String message, HttpStatus http) {
        this.message = message;
        this.httpStatus = http;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
