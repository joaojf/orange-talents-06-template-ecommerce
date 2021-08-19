package com.zup.edu.mercadolivre.pergunta;

import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @CreationTimestamp @Column(nullable = false, updatable = false)
    private final Instant criadaEm = Instant.now();

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {}

    public Pergunta(String titulo, Usuario usuario, Produto produto) {
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Instant getCriadaEm() {
        return criadaEm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return id.equals(pergunta.id) && titulo.equals(pergunta.titulo) && criadaEm.equals(pergunta.criadaEm) && usuario.equals(pergunta.usuario) && produto.equals(pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, criadaEm, usuario, produto);
    }

    @Override
    public int compareTo(Pergunta pergunta) {
        return this.titulo.compareTo(pergunta.titulo);
    }
}
