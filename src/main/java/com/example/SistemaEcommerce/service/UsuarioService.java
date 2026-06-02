package com.example.SistemaEcommerce.service;

import com.example.SistemaEcommerce.model.Usuario;
import com.example.SistemaEcommerce.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario cadastrar(Usuario usuario) {
        // Pega a senha em texto puro (ex: 123456) e transforma em um código maluco!
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        
        // Salva no banco de dados com segurança
        return repository.save(usuario);
    }
}