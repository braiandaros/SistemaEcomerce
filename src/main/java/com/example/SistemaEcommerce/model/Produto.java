package com.example.SistemaEcommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto não pode ficar em branco!")
    private String nome;
    
    @NotBlank(message = "O código SKU é obrigatório!")
    private String sku;

    @NotNull(message = "O preço é obrigatório!")
    @Positive(message = "O preço deve ser maior que zero!")
    private Double preco;
    
    private Integer quantidadeEstoque;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    // --- Getters e Setters ---
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }
    
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(Integer quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
}