package br.com.example.basiccrud.service;

import java.util.List;
import java.util.Optional;

import br.com.example.basiccrud.model.Product;

public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    Product update(Product product);
    void deleteById(Long id);
}
