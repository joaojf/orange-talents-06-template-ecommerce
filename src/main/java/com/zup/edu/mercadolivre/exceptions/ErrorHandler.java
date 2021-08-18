package com.zup.edu.mercadolivre.exceptions;

import org.springframework.validation.FieldError;

public class ErrorHandler {

    private String campo, mensagem;

    public ErrorHandler(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public ErrorHandler(FieldError fieldError) {
        this.campo = fieldError.getField();
        this.mensagem = fieldError.getDefaultMessage();
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
