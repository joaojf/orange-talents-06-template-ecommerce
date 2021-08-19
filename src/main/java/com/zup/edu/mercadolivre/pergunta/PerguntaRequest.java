package com.zup.edu.mercadolivre.pergunta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotNull
    private String titulo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PerguntaRequest(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta toModel(Usuario usuario, Produto produto) {
        return new Pergunta(this.titulo, usuario, produto);
    }

    public String getTitulo() {
        return titulo;
    }
}
