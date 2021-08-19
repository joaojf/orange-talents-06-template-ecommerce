package com.zup.edu.mercadolivre.produto;

import com.zup.edu.mercadolivre.categoria.Categoria;
import com.zup.edu.mercadolivre.imagem.Imagem;
import com.zup.edu.mercadolivre.opiniao.Opiniao;
import com.zup.edu.mercadolivre.opiniao.Opinioes;
import com.zup.edu.mercadolivre.pergunta.Pergunta;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProduto;
import com.zup.edu.mercadolivre.produto.caracteristica.CaracteristicaProdutoRequest;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, mappedBy = "produto")
    private final Set<Imagem> imagem = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntaRequests = new TreeSet<>();

    @Deprecated
    public Produto() {}

    public Produto(String nome, BigDecimal valor, Integer qtDisponivel, Collection<CaracteristicaProdutoRequest> caracteristicas, String descricao, Categoria categoria, Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.qtDisponivel = qtDisponivel;
        this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.converte(this)).collect(Collectors.toSet()));
        this.descricao = descricao;
        this.categoria = categoria;
        this.usuario = usuario;
    }

    public Set<Opiniao> getOpinioes() {
        return opinioes;
    }

    public Opinioes getOpinoess() {
        return new Opinioes(this.opinioes);
    }

    public void adicionarImagens(Set<String> link) {
        Set<Imagem> imagens = link.stream().map(url -> new Imagem(url, this)).collect(Collectors.toSet());
        this.imagem.addAll(imagens);
    }

    public Set<Imagem> getImagem() {
        return imagem;
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
