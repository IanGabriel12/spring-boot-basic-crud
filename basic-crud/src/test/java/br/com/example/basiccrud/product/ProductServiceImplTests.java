package br.com.example.basiccrud.product;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import br.com.example.basiccrud.repository.ProductRepository;
import br.com.example.basiccrud.service.ProductServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class ProductServiceImplTests {

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductRepository productRepository;

   @Test
    public void shouldThrowWhenNotFound() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> productService.findById(1L));
    }
    
}
