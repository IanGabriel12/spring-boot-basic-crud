package br.com.example.basiccrud.purchase;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.example.basiccrud.model.Address;
import br.com.example.basiccrud.model.ApiUser;
import br.com.example.basiccrud.model.Product;
import br.com.example.basiccrud.model.Purchase;
import br.com.example.basiccrud.repository.ApiUserRepository;
import br.com.example.basiccrud.repository.PurchaseRepository;
import br.com.example.basiccrud.service.PurchaseServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class PurchaseServiceImplTests {
    @Autowired
    @MockBean
    private PurchaseRepository purchaseRepository;

    @Autowired
    @MockBean
    private ApiUserRepository apiUserRepository;

    @Autowired
    private PurchaseServiceImpl purchaseService;

    ApiUser user;
    Purchase purchase;
    ArrayList<Product> products;
    ArrayList<Purchase> purchases;


    @BeforeEach
    public void setUp() {
        Address address = new Address("99999-999", "abc", "centro", "Natal", "RN", 10L);
        user = new ApiUser(1L, "Ian", "(84) 99999-9999", address);
        products = new ArrayList<>();
        products.add(new Product(1L, "Pasta", 1.0, ""));
        products.add(new Product(2L, "Chocolate", 1.0, ""));
        purchase = new Purchase(null, products, "Cartão", user);

        purchases = new ArrayList<>();
        purchases.add(purchase);
        purchases.add(purchase);
    }

    @Test
    public void shouldFindPurchase() {
        Mockito.when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));
        assertEquals(purchase, purchaseService.findById(1L));
    }

    @Test 
    public void shouldThrowIfNotFound() {
        Mockito.when(purchaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> purchaseService.findById(1L));
    }

    @Test
    public void shouldDeletePurchase() {
        Mockito.when(purchaseRepository.findById(1L)).thenReturn(Optional.of(purchase));
        Mockito.doNothing().when(purchaseRepository).deleteById(1L);
        assertDoesNotThrow(() -> purchaseService.deleteById(1L));
    }

    @Test
    public void shouldThrowIfDeleteNotFound() {
        Mockito.when(purchaseRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> purchaseService.deleteById(1L));
    }

    @Test
    public void shouldCreatePurchase() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.save(purchase)).thenReturn(purchase);
        assertEquals(purchase, purchaseService.create(purchase));
    }

    @Test
    public void createShouldThrowIfUserNotFound() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> purchaseService.create(purchase));
    }

    @Test
    public void shouldReturnPurchasesOfUser() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(purchaseRepository.findByUserId(1L)).thenReturn(purchases);
        assertEquals(purchases, purchaseService.findByUserId(1L));
    }

    @Test
    public void shouldThrowIfUserNotFound() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> purchaseService.findByUserId(1L));
    }
}
