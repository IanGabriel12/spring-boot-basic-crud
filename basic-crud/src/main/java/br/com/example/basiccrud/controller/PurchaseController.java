package br.com.example.basiccrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.basiccrud.model.Purchase;
import br.com.example.basiccrud.model.dto.PurchaseDto;
import br.com.example.basiccrud.service.PurchaseService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Purchase> create(@Valid @RequestBody PurchaseDto purchaseDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseService.create(purchaseDto.toPurchase()));
    }

    @GetMapping("/byUser/{userId}")
    public ResponseEntity<List<Purchase>> findByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok().body(purchaseService.findByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(purchaseService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        purchaseService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
