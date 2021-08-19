package com.zup.edu.mercadolivre.opiniao;

import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer nota;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    private Usuario usuarioId;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Opiniao() {}

    public Opiniao(Integer nota, String titulo, String descricao, Usuario usuarioId, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Opiniao opiniao = (Opiniao) o;
        return id.equals(opiniao.id) && nota.equals(opiniao.nota) && titulo.equals(opiniao.titulo) && descricao.equals(opiniao.descricao) && usuarioId.equals(opiniao.usuarioId) && produto.equals(opiniao.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nota, titulo, descricao, usuarioId, produto);
    }
}
