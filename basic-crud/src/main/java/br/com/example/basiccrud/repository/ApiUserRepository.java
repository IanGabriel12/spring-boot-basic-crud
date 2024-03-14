package br.com.example.basiccrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.example.basiccrud.model.ApiUser;

public interface ApiUserRepository extends JpaRepository<ApiUser, Long>{
    
}
