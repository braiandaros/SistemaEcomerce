package com.example.SistemaEcommerce.controller;

import com.example.SistemaEcommerce.model.Categoria;
import com.example.SistemaEcommerce.repository.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria novaCategoria = repository.save(categoria);
        return ResponseEntity.ok(novaCategoria);
    }
}