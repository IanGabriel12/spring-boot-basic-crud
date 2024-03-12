package br.com.example.basiccrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.basiccrud.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
