package com.zup.edu.mercadolivre.produto;

import com.zup.edu.mercadolivre.categoria.CategoriaRepository;
import com.zup.edu.mercadolivre.exceptions.ExceptionNotFound;
import com.zup.edu.mercadolivre.produto.dtos.ProdutoRequest;
import com.zup.edu.mercadolivre.produto.dtos.ProdutoResponse;
import com.zup.edu.mercadolivre.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "/produtos")
    public ResponseEntity<?> insert(@Valid @RequestBody ProdutoRequest produtoRequest, Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Produto produto = produtoRequest.toModel(categoriaRepository, usuario);
        produtoRepositoy.save(produto);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping(value = "/produtos/{id}")
    public ResponseEntity<?> detalhes(@PathVariable(value = "id") Long id) {
        Produto produto = produtoRepositoy.findById(id).orElseThrow(() -> new ExceptionNotFound("id " + id + " não encontrado",
                HttpStatus.NOT_FOUND));
        return ResponseEntity.ok().body(new ProdutoResponse(produto));
    }
}
