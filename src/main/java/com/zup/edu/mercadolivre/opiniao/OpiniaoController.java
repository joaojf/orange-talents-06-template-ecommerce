package com.zup.edu.mercadolivre.opiniao;

import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
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
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;
    private final EntityManager entityManager;

    @Autowired
    public OpiniaoController(OpiniaoRepository opiniaoRepository, EntityManager entityManager) {
        this.opiniaoRepository = opiniaoRepository;
        this.entityManager = entityManager;
    }



    @Transactional
    @PostMapping(value = "/produtos/opinioes/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id, @Valid @RequestBody OpiniaoRequest opiniaoRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = entityManager.find(Produto.class, id);
        Opiniao opiniao = opiniaoRequest.toModel(produto, usuario);
        opiniaoRepository.save(opiniao);
        return ResponseEntity.ok().build();
    }

}
