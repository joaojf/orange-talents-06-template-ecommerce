package com.zup.edu.mercadolivre.produto;

import com.zup.edu.mercadolivre.categoria.Categoria;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    private Integer qtDisponivel;

    @Column(nullable = false)
    private String descricao;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST, mappedBy = "produto")
    private final Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;

    @ManyToOne
    private Usuario usuario;

    @CreationTimestamp
    private final Instant criadoEm = Instant.now();

    @Deprecated
    public Produto() {
    }

    public Produto(String nome, BigDecimal valor, Integer qtDisponivel, Collection<CaracteristicaProdutoRequest> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.qtDisponivel = qtDisponivel;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.converte(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
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

    public String getDescricao() {
        return descricao;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }


}
