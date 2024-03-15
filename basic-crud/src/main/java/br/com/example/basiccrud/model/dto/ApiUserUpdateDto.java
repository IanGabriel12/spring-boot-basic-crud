package br.com.example.basiccrud.model.dto;

import br.com.example.basiccrud.model.ApiUser;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ApiUserUpdateDto extends ApiUserDto {
    @NotNull
    private Long id;

    public ApiUserUpdateDto() {
        super();
    }
    
    @Override
    public ApiUser toApiUser() {
        return new ApiUser(id, name, phoneNumber, address.toAddress());
    }
}
