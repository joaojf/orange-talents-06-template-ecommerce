package com.zup.edu.mercadolivre.compra;

import com.zup.edu.mercadolivre.compra.dtos.CompraRequest;
import com.zup.edu.mercadolivre.compra.dtos.FinalizaCompraRequest;
import com.zup.edu.mercadolivre.compra.simulacoeslocais.GerarNotaFiscal;
import com.zup.edu.mercadolivre.compra.simulacoeslocais.GerarPontuacaoRanking;
import com.zup.edu.mercadolivre.exceptions.ExceptionNotFound;
import com.zup.edu.mercadolivre.pagamento.EnumStatusTransacao;
import com.zup.edu.mercadolivre.pagamento.Transacao;
import com.zup.edu.mercadolivre.pergunta.email.EnviarEmail;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class CompraController {

    private final EntityManager entityManager;
    private final EnviarEmail enviarEmail;
    private final CompraRepository compraRepository;
    private final GerarNotaFiscal gerarNotaFiscal;
    private final GerarPontuacaoRanking gerarPontuacaoRanking;

    @Autowired
    public CompraController(EntityManager entityManager, EnviarEmail enviarEmail, CompraRepository compraRepository, GerarNotaFiscal gerarNotaFiscal, GerarPontuacaoRanking gerarPontuacaoRanking) {
        this.entityManager = entityManager;
        this.enviarEmail = enviarEmail;
        this.compraRepository = compraRepository;
        this.gerarNotaFiscal = gerarNotaFiscal;
        this.gerarPontuacaoRanking = gerarPontuacaoRanking;
    }

    @Transactional
    @PostMapping(value = "/compras")
    public ResponseEntity<?> insert(@Valid @RequestBody CompraRequest compraRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Compra compra = compraRequest.toModel(entityManager, usuario);
        entityManager.persist(compra);
        enviarEmail.desejoDeCompra(compra);
        String pagar = compra.getGateway().financeiro().pagar(compra.getId());
        return ResponseEntity.status(302).body(pagar);
    }

    @Transactional
    @PostMapping(value = "/compra/{idCompra}")
    public ResponseEntity<?> fecharCompra(@PathVariable String idCompra) {
        Compra compra = compraRepository
                .findById(idCompra)
                .orElseThrow(() -> new ExceptionNotFound("Id: " + idCompra + " não encontrado", HttpStatus.NOT_FOUND));

        String retornoGateway = compra.getGateway().financeiro().processarTransacao(compra.getId()).toString();

        FinalizaCompraRequest finalizaCompraRequest = new FinalizaCompraRequest(compra.getId(), retornoGateway, compra.getGateway());
        Transacao transacao = finalizaCompraRequest.toModel(compraRepository);

        entityManager.persist(transacao);

        if (transacao.getStatusCompra() == EnumStatusTransacao.SUCESSO) {
            compra.alterarStatusCompra(EnumStatusCompra.FINALIZADA);
            compraRepository.save(compra);
            gerarNotaFiscal.processar(compra);
            gerarPontuacaoRanking.processar(compra);
            enviarEmail.transacaoEfetuada(transacao);
        } else {
            enviarEmail.avisoTransacaoRecusada(transacao);
        }

        return ResponseEntity.ok().build();
    }

}
