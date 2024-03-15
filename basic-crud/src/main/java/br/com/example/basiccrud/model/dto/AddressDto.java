package br.com.example.basiccrud.model.dto;

import br.com.example.basiccrud.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class AddressDto {
    @NotBlank
    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    private String cep;

    @NotBlank
    private String street;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    private Long number;

    public Address toAddress() {
        return new Address(cep, street, neighborhood, city, state, number);
    }
}
