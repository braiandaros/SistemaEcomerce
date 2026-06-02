package com.example.SistemaEcommerce.dto;

// O 'record' é uma forma moderna e curta de criar classes no Java que só transportam dados!
public record LoginDTO(String email, String senha) {
}