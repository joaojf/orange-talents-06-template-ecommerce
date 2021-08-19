package com.zup.edu.mercadolivre.imagem;

import com.zup.edu.mercadolivre.produto.Produto;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Imagem imagem = (Imagem) o;
        return id.equals(imagem.id) && url.equals(imagem.url) && produto.equals(imagem.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, produto);
    }
}
