package br.com.example.basiccrud.model.dto;

import java.util.ArrayList;
import java.util.List;

import br.com.example.basiccrud.model.ApiUser;
import br.com.example.basiccrud.model.Product;
import br.com.example.basiccrud.model.Purchase;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PurchaseDto {
    @NotNull
    private Long userId;

    @NotNull
    @NotEmpty
    private List<Long> productIds;

    @NotBlank
    private String paymentMethod;

    public Purchase toPurchase() {
        Purchase purchase = new Purchase();
        ApiUser user = new ApiUser();
        user.setId(userId);
        ArrayList<Product> products = new ArrayList<>();
        
        productIds.forEach(id -> {
            Product p = new Product();
            p.setId(id);
            products.add(p);
        });
        purchase.setUser(user);
        purchase.setProducts(products);
        purchase.setPaymentMethod(paymentMethod);
        return purchase;
    }
}
