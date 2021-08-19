package com.zup.edu.mercadolivre.imagem;

import com.zup.edu.mercadolivre.imagem.uploads.ImagemUploadFake;
import com.zup.edu.mercadolivre.produto.Produto;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Set;

@RestController
public class ImagemController {

    private final EntityManager entityManager;
    private final ImagemUploadFake imagemUploadFake;

    @Autowired
    public ImagemController(EntityManager entityManager, ImagemUploadFake imagemUploadFake) {
        this.entityManager = entityManager;
        this.imagemUploadFake = imagemUploadFake;
    }

    @Transactional
    @PostMapping(value = "produtos/imagens/{id}")
    public ResponseEntity<?> insert(@PathVariable Long id, @Valid ImagemRequest imagemRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Set<String> url = imagemUploadFake.enviar(imagemRequest.getListaImagens());
        Produto produto = entityManager.find(Produto.class, id);
        if (!produto.getUsuario().equals(usuario)) {
            return ResponseEntity.unprocessableEntity().body("Produto não pertence ao usuário");
        }
        produto.adicionarImagens(url);
        entityManager.merge(produto);
        return ResponseEntity.ok().build();
    }
}
