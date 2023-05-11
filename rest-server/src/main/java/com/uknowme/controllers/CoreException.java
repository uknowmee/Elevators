package com.uknowme.controllers;

import org.springframework.http.HttpStatus;

public abstract class CoreException extends RuntimeException{
    private final HttpStatus httpStatus;

    protected CoreException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
