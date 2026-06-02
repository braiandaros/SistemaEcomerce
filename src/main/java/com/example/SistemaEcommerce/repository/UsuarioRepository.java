package com.example.SistemaEcommerce.repository;

import com.example.SistemaEcommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Método mágico que o Spring Security vai usar para achar o usuário na hora do login!
    Optional<Usuario> findByEmail(String email);
}