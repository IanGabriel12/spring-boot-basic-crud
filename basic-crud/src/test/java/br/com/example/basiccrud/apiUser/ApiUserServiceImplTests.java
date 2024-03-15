package br.com.example.basiccrud.apiUser;

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
import br.com.example.basiccrud.repository.ApiUserRepository;
import br.com.example.basiccrud.service.ApiUserServiceImpl;
import jakarta.persistence.EntityNotFoundException;

@SpringBootTest
public class ApiUserServiceImplTests {
    @Autowired
    private ApiUserServiceImpl apiUserService;

    @MockBean
    private ApiUserRepository apiUserRepository;

    private ApiUser apiUser;

    @BeforeEach
    public void setUp() throws Exception {
        apiUser = new ApiUser();
        Address address = new Address("89999-000", "R. teste", "Centro", "Natal", "RN", 85L);
        apiUser.setName("Ian Gabriel");
        apiUser.setPhoneNumber("(99) 99999-9999");
        apiUser.setAddress(address);
    }

    @SuppressWarnings("null")
    @Test
    public void shouldSaveUser() {
        Mockito.when(apiUserRepository.save(apiUser)).thenReturn(new ApiUser(
            1L,
            apiUser.getName(),
            apiUser.getPhoneNumber(),
            apiUser.getAddress()
        ));
        apiUser = apiUserService.save(apiUser);
        assertEquals(apiUser.getId(), 1L);
    }

    @Test
    public void shouldUpdateUser() {
        apiUser.setId(1L);
        apiUser.setName("Editado");
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.of(apiUser));
        Mockito.when(apiUserRepository.save(apiUser)).thenReturn(new ApiUser(
            1L,
            apiUser.getName(),
            apiUser.getPhoneNumber(),
            apiUser.getAddress()
        ));

        ApiUser userReturned = apiUserService.update(apiUser);
        assertEquals(apiUser, userReturned);
    }

    @Test
    public void shouldFindAll() {
        ArrayList<ApiUser> list = new ArrayList<>();
        list.add(apiUser);
        list.add(apiUser);
        Mockito.when(apiUserRepository.findAll()).thenReturn(list);
        assertEquals(apiUserService.findAll(), list);
    }


    @Test
    public void shouldFindById() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.of(apiUser));
        assertEquals(apiUserService.findById(1L), apiUser);
    }

    @Test
    public void shouldDeteleById() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.of(apiUser));
        Mockito.doNothing().when(apiUserRepository).deleteById(1L);
        assertDoesNotThrow(() -> apiUserService.deleteById(1L));
    }

    @Test
    public void findByIdShouldThrowWhenNotFound() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> apiUserService.findById(1L));
    }

    @Test
    public void updateShouldThrowWhenNotFound() {
        apiUser.setId(1L);
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> apiUserService.update(apiUser));
    }

    @Test
    public void deleteShouldThrowWhenNotFound() {
        Mockito.when(apiUserRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> apiUserService.deleteById(1L));
    }
}
