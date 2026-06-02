package com.example.SistemaEcommerce.repository;

import com.example.SistemaEcommerce.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	// O Spring lê isso e entende: "Busque todos os produtos onde o categoria_id seja igual ao valor passado"
    List<Produto> findByCategoriaId(Long categoriaId);
    
    // "Buscar por Nome Contendo (Ignorando Maiúsculas/Minúsculas)"
    List<Produto> findByNomeContainingIgnoreCase(String nome);
}