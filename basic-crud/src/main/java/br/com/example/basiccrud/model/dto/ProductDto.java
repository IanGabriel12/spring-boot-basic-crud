package br.com.example.basiccrud.model.dto;

import br.com.example.basiccrud.model.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank(message = "Name is required")
    protected String name;

    @NotNull(message = "Value is required")
    protected Double value;

    protected String description;

    public Product toProduct() {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setValue(value);
        return p;
    }
}
