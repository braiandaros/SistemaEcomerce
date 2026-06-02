package com.example.SistemaEcommerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SistemaEcommerce.model.Produto;
import com.example.SistemaEcommerce.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // POST: Criar
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@Valid @RequestBody Produto produto) {
        Produto novoProduto = service.salvar(produto);
        return ResponseEntity.ok(novoProduto);
    }

    // GET: Listar todos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    // GET: Buscar um específico pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable Long id) {
        Optional<Produto> produto = service.buscarPorId(id);
        if (produto.isPresent()) {
            return ResponseEntity.ok(produto.get());
        }
        return ResponseEntity.notFound().build(); // Retorna erro 404 se não achar
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<Produto>> buscarPorCategoria(@PathVariable Long categoriaId) {
        List<Produto> produtos = service.buscarPorCategoria(categoriaId);
        
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se a categoria existir mas não tiver produtos
        }
        return ResponseEntity.ok(produtos);
    }
    
    // PUT: Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produto produto) {
        Produto produtoAtualizado = service.atualizar(id, produto);
        if (produtoAtualizado != null) {
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE: Deletar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build(); // Retorna 204 (Sucesso, sem conteúdo)
        }
        return ResponseEntity.notFound().build();
    }
    
    // GET: Buscar produtos por nome (Barra de pesquisa)
    @GetMapping("/busca")
    public ResponseEntity<List<Produto>> buscarPorNome(@RequestParam String nome) {
        List<Produto> produtos = service.buscarPorNome(nome);
        
        if (produtos.isEmpty()) {
            return ResponseEntity.noContent().build(); // Retorna 204 se não achar nada
        }
        return ResponseEntity.ok(produtos);
    }
}