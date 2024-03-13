package br.com.example.basiccrud.model.dto;

import br.com.example.basiccrud.model.Product;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductUpdateDto extends ProductDto {
    @NotNull
    private Long id;

    ProductUpdateDto() {
        super();
    }

    @Override
    public Product toProduct() {
        Product p = new Product();
        p.setId(id);
        p.setName(name);
        p.setDescription(description);
        p.setValue(value);
        return p;
    }
}
