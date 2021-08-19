package com.zup.edu.mercadolivre.produto;

import com.zup.edu.mercadolivre.categoria.Categoria;
import com.zup.edu.mercadolivre.categoria.CategoriaRepository;
import com.zup.edu.mercadolivre.exceptions.ExisteValid;
import com.zup.edu.mercadolivre.exceptions.NaoHaNomeRepetidoNaListaValid;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ProdutoRequest {

    @NotBlank
    private final String nome;

    @NotNull
    @Positive
    private final BigDecimal valor;

    @Positive
    private final Integer qtDisponivel;

    @Size(min = 3)
    @NotNull
    @NaoHaNomeRepetidoNaListaValid
    List<CaracteristicaProdutoRequest> caracteristicas;

    @NotBlank
    @Length(max = 1000)
    private final String descricao;

    @NotNull
    @ExisteValid(entidade = Categoria.class, atributo = "id")
    private final Long categoriaId;

    public ProdutoRequest(String nome, BigDecimal valor, Integer qtDisponivel, List<CaracteristicaProdutoRequest> caracteristicas, String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.qtDisponivel = qtDisponivel;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> byCategoriaId = categoriaRepository.findById(categoriaId);
        return new Produto(this.nome, this.valor, this.qtDisponivel, this.caracteristicas, this.descricao, byCategoriaId.get(), usuario);
    }


    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQtDisponivel() {
        return qtDisponivel;
    }

    public List<CaracteristicaProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }
}
