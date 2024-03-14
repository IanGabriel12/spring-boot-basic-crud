package br.com.example.basiccrud.service;

import java.util.List;

import br.com.example.basiccrud.model.ApiUser;

public interface ApiUserService {
    List<ApiUser> findAll();
    ApiUser findById(Long id);
    ApiUser save(ApiUser user);
    ApiUser update(ApiUser user);
    void deleteById(Long id);
}