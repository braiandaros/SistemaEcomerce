package com.example.SistemaEcommerce.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.SistemaEcommerce.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    
    // Essa é a "senha-mestra" da sua fábrica de tokens. (Em projetos reais, fica oculta!)
    private final String secret = "minha-chave-secreta-ecommerce";

    public String gerarToken(Usuario usuario) {
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        
        return JWT.create()
                .withIssuer("SistemaEcommerce") // Quem emitiu
                .withSubject(usuario.getEmail()) // Quem é o dono do token
                .withClaim("role", usuario.getRole()) // A pulseira: ADMIN ou USER
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 2)) // Expira em 2 horas
                .sign(algoritmo);
    }
    
    public String validarToken(String token) {
        try {
            Algorithm algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer("SistemaEcommerce")
                    .build()
                    .verify(token)
                    .getSubject(); // Se der certo, devolve o email do dono do token
        } catch (Exception exception) {
            return ""; // Se for um token falso ou vencido, devolve vazio
        }
    }
}