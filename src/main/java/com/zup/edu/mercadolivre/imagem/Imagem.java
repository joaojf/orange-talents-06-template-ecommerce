package com.zup.edu.mercadolivre.imagem;

import com.zup.edu.mercadolivre.produto.Produto;

import javax.persistence.*;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Imagem() {}

    public Imagem(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getLink() {
        return url;
    }
}
