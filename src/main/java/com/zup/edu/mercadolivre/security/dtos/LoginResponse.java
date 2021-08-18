package com.zup.edu.mercadolivre.security.dtos;

public class LoginResponse {

    private final String token, tipo;

    public LoginResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
