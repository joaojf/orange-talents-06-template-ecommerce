package com.zup.edu.mercadolivre.compra;

import com.zup.edu.mercadolivre.exceptions.ExceptionNotFound;
import com.zup.edu.mercadolivre.exceptions.ExisteValid;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.http.HttpStatus;

import javax.persistence.EntityManager;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class CompraRequest {

    @Positive @NotNull
    private Integer quantidade;

    @NotNull @ExisteValid(entidade = Produto.class, atributo = "id")
    private Long idProduto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EnumGatewayPagamento gateway;

    public CompraRequest(Integer quantidade, Long idProduto, EnumGatewayPagamento gateway) {
        this.quantidade = quantidade;
        this.idProduto = idProduto;
        this.gateway = gateway;
    }

    public Compra toModel(EntityManager entityManager, Usuario usuario) {
        Produto produto = entityManager.find(Produto.class, idProduto);
        boolean abaterEstoque = produto.abaterEstoque(this.quantidade);
        if (abaterEstoque) {
            BigDecimal valorCompra = produto.getValor().multiply(BigDecimal.valueOf(this.quantidade));
            return new Compra(produto, this.quantidade, valorCompra, usuario, this.gateway);
        } else {
            throw new ExceptionNotFound("Quantidade do produto indisponível", HttpStatus.BAD_REQUEST);
        }
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public EnumGatewayPagamento getGateway() {
        return gateway;
    }
}
