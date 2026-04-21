package com.example.SistemaEcommerce.controller;

import com.example.SistemaEcommerce.model.Produto;
import com.example.SistemaEcommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    // POST: Criar
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
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

    // PUT: Atualizar
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
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
}