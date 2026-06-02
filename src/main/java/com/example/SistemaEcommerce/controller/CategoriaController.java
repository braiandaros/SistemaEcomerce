package com.example.SistemaEcommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaEcommerce.model.Categoria;
import com.example.SistemaEcommerce.repository.CategoriaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
        Categoria novaCategoria = repository.save(categoria);
        return ResponseEntity.ok(novaCategoria);
    }
}