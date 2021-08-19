package com.zup.edu.mercadolivre.produto.dtos;

import com.zup.edu.mercadolivre.opiniao.Opinioes;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaResponse;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ProdutoResponse {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private Set<CaracteristicaResponse> caracteristicas;
    private Set<String> linksImagens;
    private SortedSet<String> perguntas;
    private Set<Map<String, String>> opinioes;
    private Double mediaNotas;
    private int totalNotas;

    public ProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.valor = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapeiaCaracteristicas(CaracteristicaResponse::new);
        this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
        this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
        Opinioes opinioes = produto.getOpinoess();
        this.opinioes = opinioes.mapeiaOpinioes(opiniao -> {
            return Map.of("titulo", opiniao.getTitulo(),"descricao", opiniao.getDescricao());
        });
        this.mediaNotas = opinioes.media();
        this.totalNotas = opinioes.total();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<String> getLinksImagens() {
        return linksImagens;
    }

    public SortedSet<String> getPerguntas() {
        return perguntas;
    }

    public Set<Map<String, String>> getOpinioes() {
        return opinioes;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public int getTotalNotas() {
        return totalNotas;
    }
}
