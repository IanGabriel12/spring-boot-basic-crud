package br.com.example.basiccrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.basiccrud.model.ApiUser;
import br.com.example.basiccrud.model.dto.ApiUserDto;
import br.com.example.basiccrud.model.dto.ApiUserUpdateDto;
import br.com.example.basiccrud.service.ApiUserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/v1/users")
public class ApiUserController {
    @Autowired
    private ApiUserService apiUserService;

    @GetMapping
    public ResponseEntity<List<ApiUser>> findAll() {
        return ResponseEntity.ok().body(apiUserService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiUser> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(apiUserService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ApiUser> create(@Valid @RequestBody ApiUserDto apiUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(apiUserService.save(apiUserDto.toApiUser()));
    }

    @PutMapping
    public ResponseEntity<ApiUser> update(@Valid @RequestBody ApiUserUpdateDto apiUserDto) {
        return ResponseEntity.ok().body(apiUserService.update(apiUserDto.toApiUser()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        apiUserService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }
}
