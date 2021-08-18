package com.zup.edu.mercadolivre.security;

import com.zup.edu.mercadolivre.usuario.Usuario;
import com.zup.edu.mercadolivre.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AutenticacaoService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public AutenticacaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userlogin) throws UsernameNotFoundException {
        Optional<Usuario> findByLogin = usuarioRepository.findByLogin(userlogin);
        if (findByLogin.isPresent()) {
            return findByLogin.get();
        } throw new UsernameNotFoundException("Dados inválidos");
    }
}
