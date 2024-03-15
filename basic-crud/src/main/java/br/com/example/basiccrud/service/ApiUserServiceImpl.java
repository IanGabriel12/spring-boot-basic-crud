package br.com.example.basiccrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.example.basiccrud.model.ApiUser;
import br.com.example.basiccrud.repository.ApiUserRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ApiUserServiceImpl implements ApiUserService {

    @Autowired
    ApiUserRepository apiUserRepository;

    @Override
    public List<ApiUser> findAll() {
        return apiUserRepository.findAll();
    }

    @Override
    public ApiUser findById(Long id) {
        Optional<ApiUser> apiUser = apiUserRepository.findById(id);
        if(apiUser.isEmpty()) {
            throw new EntityNotFoundException("User not found with id " + id);
        }
        return apiUser.get();
    }

    @Override
    public ApiUser save(ApiUser user) {
        user.setId(null);
        return apiUserRepository.save(user);
    }

    @Override
    public ApiUser update(ApiUser user) {
        findById(user.getId());
        return apiUserRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        findById(id);
        apiUserRepository.deleteById(id);
    }
    
}
