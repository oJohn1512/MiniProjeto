package com.example.miniProjeto.Exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    String message;
    HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
