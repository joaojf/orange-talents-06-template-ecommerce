package com.zup.edu.mercadolivre.pergunta.email;

import com.zup.edu.mercadolivre.pergunta.Pergunta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnviarEmail {

    private final Email email;

    @Autowired
    public EnviarEmail(Email email) {
        this.email = email;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EnviarEmail.class);

    public void pergunta(Pergunta pergunta) {
        email.enviar("<html>...</html>", pergunta.getTitulo(),
                pergunta.getUsuario().getLogin(),
                pergunta.getProduto().getUsuario().getLogin());
    }


}
