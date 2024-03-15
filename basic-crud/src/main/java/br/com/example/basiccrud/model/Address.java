package br.com.example.basiccrud.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    @Nonnull
    private String cep;

    @Nonnull
    private String street;
    
    @Nonnull
    private String neighborhood;

    @Nonnull
    private String city;

    @Nonnull
    private String state;

    private Long number;
}
