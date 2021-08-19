package com.zup.edu.mercadolivre.produto.caracteristica;

import com.zup.edu.mercadolivre.produto.Produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicaProdutoRequest {

    @NotBlank
    private final String nome;

    @NotBlank
    private final String descricao;

    public CaracteristicaProdutoRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto converte(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
