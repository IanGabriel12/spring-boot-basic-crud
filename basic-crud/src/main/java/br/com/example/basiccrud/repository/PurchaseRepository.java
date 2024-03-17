package br.com.example.basiccrud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.basiccrud.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long>{
    public List<Purchase> findByUserId(Long id);
}
