package com.zup.edu.mercadolivre.security.dtos;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {

    private final String email, password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
