package com.zup.edu.mercadolivre.pergunta;

import com.zup.edu.mercadolivre.exceptions.ExceptionNotFound;
import com.zup.edu.mercadolivre.pergunta.email.EnviarEmail;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.produto.ProdutoRepositoy;
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
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class PerguntaController {

    @PersistenceContext
    private final EntityManager entityManager;
    private final ProdutoRepositoy produtoRepositoy;
    private final EnviarEmail enviarEmail;

    @Autowired
    public PerguntaController(EntityManager entityManager, ProdutoRepositoy produtoRepositoy, EnviarEmail enviarEmail) {
        this.entityManager = entityManager;
        this.produtoRepositoy = produtoRepositoy;
        this.enviarEmail = enviarEmail;
    }

    @Transactional
    @PostMapping(value = "/facaUmaPergunta/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id, @Valid @RequestBody PerguntaRequest perguntaRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = produtoRepositoy.findById(id).orElseThrow(() -> new ExceptionNotFound("Id " + id + " não encontrado", HttpStatus.NOT_FOUND));
        Pergunta pergunta = perguntaRequest.toModel(usuario, produto);
        if (produto.getUsuario().equals(usuario)) {
            return ResponseEntity.unprocessableEntity().body("Produto pertence ao usuário");
        }
        entityManager.persist(pergunta);
        enviarEmail.pergunta(pergunta);
        return ResponseEntity.ok().build();
    }

}
