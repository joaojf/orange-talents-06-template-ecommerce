package com.zup.edu.mercadolivre.produto.caracteristica;

public class CaracteristicaResponse {

    private String nome, descricao;

    public CaracteristicaResponse(CaracteristicaProduto caracteristicaProduto) {
        this.nome = caracteristicaProduto.getNome();
        this.descricao = caracteristicaProduto.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
