package com.zup.edu.mercadolivre.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    @PostMapping(value = "/usuarios")
    public ResponseEntity<?> insert(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        Usuario usuario = usuarioRequest.toModel();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
    }

}
