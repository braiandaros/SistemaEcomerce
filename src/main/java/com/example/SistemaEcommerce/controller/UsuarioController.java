package com.example.SistemaEcommerce.controller;

import com.example.SistemaEcommerce.dto.LoginDTO;
import com.example.SistemaEcommerce.model.Usuario;
import com.example.SistemaEcommerce.repository.UsuarioRepository;
import com.example.SistemaEcommerce.security.TokenService;
import com.example.SistemaEcommerce.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioRepository repository; // Adicionamos o banco aqui

    @Autowired
    private PasswordEncoder passwordEncoder; // O comparador de senhas

    @Autowired
    private TokenService tokenService; // A nossa fábrica de pulseiras

    @PostMapping("/registrar")
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        Usuario novoUsuario = service.cadastrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    // NOVA ROTA: O Login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO dados) {
        // 1. Busca o usuário no banco de dados pelo e-mail
        Optional<Usuario> usuarioOp = repository.findByEmail(dados.email());

        // Se o e-mail não existir, Erro 401 (Não Autorizado)
        if (usuarioOp.isEmpty()) {
            return ResponseEntity.status(401).body("Usuário ou senha incorretos.");
        }

        Usuario usuario = usuarioOp.get();

        // 2. O Spring Security compara a senha digitada com a criptografada do banco!
        if (passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            
            // 3. Se deu tudo certo, gera o Token JWT e devolve para o cliente
            String token = tokenService.gerarToken(usuario);
            return ResponseEntity.ok(token);
        }

        // Se a senha estiver errada, Erro 401
        return ResponseEntity.status(401).body("Usuário ou senha incorretos.");
    }
}