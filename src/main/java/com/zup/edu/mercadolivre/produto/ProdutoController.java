package com.zup.edu.mercadolivre.produto;

import com.zup.edu.mercadolivre.categoria.CategoriaRepository;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ProdutoController {

    private final ProdutoRepositoy produtoRepositoy;
    private final CategoriaRepository categoriaRepository;

    @Autowired
    public ProdutoController(ProdutoRepositoy produtoRepositoy, CategoriaRepository categoriaRepository) {
        this.produtoRepositoy = produtoRepositoy;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    @PostMapping(value = "/novo/produto")
    public ResponseEntity<?> insert(@Valid @RequestBody ProdutoRequest produtoRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = produtoRequest.toModel(categoriaRepository, usuario);
        produtoRepositoy.save(produto);
        return ResponseEntity.ok().build();
    }
}
