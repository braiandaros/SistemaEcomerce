package com.example.SistemaEcommerce.security;

import com.example.SistemaEcommerce.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recuperarToken(request);
        
        if (token != null) {
            var email = tokenService.validarToken(token);
            var usuario = repository.findByEmail(email);

            if (usuario.isPresent()) {
                // Monta a autorização dizendo ao Spring: "Esse cara pode passar!"
                // Obs: Como não estamos usando UserDetails padrão ainda, liberamos o acesso básico
                var authentication = new UsernamePasswordAuthenticationToken(usuario.get(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response); // Manda seguir o fluxo
    }

    private String recuperarToken(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader == null) return null;
        return authHeader.replace("Bearer ", ""); // Tira a palavra Bearer e deixa só o código
    }
}