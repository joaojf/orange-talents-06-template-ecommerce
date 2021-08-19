package com.zup.edu.mercadolivre.compra;

import com.zup.edu.mercadolivre.pergunta.email.EnviarEmail;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
public class CompraController {

    private final EntityManager entityManager;
    private final EnviarEmail enviarEmail;

    @Autowired
    public CompraController(EntityManager entityManager, EnviarEmail enviarEmail) {
        this.entityManager = entityManager;
        this.enviarEmail = enviarEmail;
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



}
