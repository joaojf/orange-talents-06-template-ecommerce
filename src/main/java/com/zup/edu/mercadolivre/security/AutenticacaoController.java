package com.zup.edu.mercadolivre.security;

import com.zup.edu.mercadolivre.security.dtos.LoginRequest;
import com.zup.edu.mercadolivre.security.dtos.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AutenticacaoController {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;

    @Autowired
    public AutenticacaoController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @Transactional
    @PostMapping(value = "/auth")
    public ResponseEntity<LoginResponse> auntentica(@Valid @RequestBody LoginRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converte();
        try {
            Authentication authenticate = authenticationManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authenticate);
            return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
