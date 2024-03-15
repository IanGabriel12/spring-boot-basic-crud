package br.com.example.basiccrud.model.dto;

import br.com.example.basiccrud.model.ApiUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class ApiUserDto {
    @NotBlank
    protected String name;

    @NotBlank
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$")
    protected String phoneNumber;

    @Valid
    protected AddressDto address;

    public ApiUser toApiUser() {
        return new ApiUser(null, name, phoneNumber, address.toAddress());
    }
}
