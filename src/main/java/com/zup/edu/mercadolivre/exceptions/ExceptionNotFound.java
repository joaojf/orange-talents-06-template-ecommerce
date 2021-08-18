package com.zup.edu.mercadolivre.exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionNotFound extends RuntimeException {

    private HttpStatus httpStatus;
    public ExceptionNotFound(String msg, HttpStatus notFound) {
        super(msg);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
