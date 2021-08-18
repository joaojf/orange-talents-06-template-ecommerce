package com.zup.edu.mercadolivre.categoria;

import com.zup.edu.mercadolivre.exceptions.ExisteValid;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    private final String nome;

    @ExisteValid(entidade = Categoria.class, atributo = "id", message = "Categoria não encontrada")
    private final Long idCategoriaMae;

    public CategoriaRequest(String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        Categoria categoria = idCategoriaMae != null ? categoriaRepository.findById(idCategoriaMae).get() : null;
        return new Categoria(this.nome, categoria);
    }


}
