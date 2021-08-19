package com.zup.edu.mercadolivre.compra;

import com.zup.edu.mercadolivre.pagamento.Transacao;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Compra {

    @Id
    private final String id = UUID.randomUUID().toString().replace("-","");

    @ManyToOne
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @ManyToOne
    private Usuario usuario;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumGatewayPagamento gateway;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumStatusCompra status;

    @Column(nullable = false, updatable = false)
    private final Instant dataModificacao = Instant.now();

    @OneToMany(mappedBy = "compra")
    private final Set<Transacao> transacoes = new HashSet<>();

    @Deprecated
    private Compra() {}

    public Compra(Produto produto, @Positive @NotNull Integer quantidade, BigDecimal valorTotal, Usuario usuario, EnumGatewayPagamento gateway) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.gateway = gateway;
        this.status = EnumStatusCompra.INICIADA;
    }

    public String getId() { return id; }

    public EnumGatewayPagamento getGateway() {
        return gateway;
    }

    public Produto getProduto() { return produto; }

    public Integer getQuantidade() { return quantidade; }

    public BigDecimal getValorTotal(){ return valorTotal; }

    public Usuario getUsuario() { return usuario; }

    public void alterarStatusCompra(EnumStatusCompra novoStatus){
        this.status = novoStatus;
    }
}
