package br.com.example.basiccrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.basiccrud.model.ApiUser;
import br.com.example.basiccrud.model.Product;
import br.com.example.basiccrud.model.Purchase;
import br.com.example.basiccrud.repository.ProductRepository;
import br.com.example.basiccrud.repository.PurchaseRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ApiUserService apiUserService;

    @Override
    public Purchase findById(Long id) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        if(purchaseOptional.isEmpty()) throw new EntityNotFoundException("Purchase not found with id " + id);
        return purchaseOptional.get();
    }

    @Override
    public List<Purchase> findByUserId(Long userId) {
        apiUserService.findById(userId);
        return purchaseRepository.findByUserId(userId);
    }

    @Override
    public Purchase create(Purchase purchase) {
        ApiUser user = apiUserService.findById(purchase.getUser().getId());
        ArrayList<Long> productIds = new ArrayList<>();
        purchase.getProducts().forEach(product -> productIds.add(product.getId()));
        List<Product> products = productRepository.findAllById(productIds);

        if(products.size() != productIds.size()) {
            throw new EntityNotFoundException("Some products were not found");
        }

        purchase.setProducts(products);
        purchase.setUser(user);
        return purchaseRepository.save(purchase);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Purchase> purchaseOptional = purchaseRepository.findById(id);
        if(purchaseOptional.isEmpty()) throw new EntityNotFoundException("Purchase not found with id " + id);
        purchaseRepository.deleteById(id);
    }
    
}
