package br.com.example.basiccrud.service;

import java.util.List;

import br.com.example.basiccrud.model.Purchase;

public interface PurchaseService {
    public Purchase findById(Long id);
    public List<Purchase> findByUserId(Long userId);
    public Purchase create(Purchase purchase);
    public void deleteById(Long id);
}
