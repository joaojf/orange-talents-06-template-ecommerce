package com.zup.edu.mercadolivre.usuario;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;

    @NotBlank @Length(min = 6)
    private String password;

    @Deprecated
    public UsuarioRequest() {}

    public UsuarioRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Usuario toModel() {
        return new Usuario(this.login, this.password);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
