package com.example.SistemaEcommerce.service;

import com.example.SistemaEcommerce.model.Produto;
import com.example.SistemaEcommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    // CREATE (Já tínhamos)
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    // READ: Listar todos
    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    // READ: Buscar por ID
    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // UPDATE: Atualizar produto existente
    public Produto atualizar(Long id, Produto produtoAtualizado) {
        if (repository.existsById(id)) {
            produtoAtualizado.setId(id); // Garante que estamos atualizando o ID correto
            return repository.save(produtoAtualizado);
        }
        return null; // Retorna nulo se o produto não existir
    }

    // DELETE: Deletar produto
    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}