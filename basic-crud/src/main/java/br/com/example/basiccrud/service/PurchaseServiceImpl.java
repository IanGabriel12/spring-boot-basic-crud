package br.com.example.basiccrud.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.example.basiccrud.model.Purchase;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    @Override
    public Purchase findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public List<Purchase> findByUserId(Long userId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUserId'");
    }

    @Override
    public Purchase create(Purchase purchase) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }
    
}
