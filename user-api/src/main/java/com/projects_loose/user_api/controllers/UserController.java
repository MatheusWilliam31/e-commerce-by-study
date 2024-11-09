package com.projects_loose.user_api.controllers;

import com.projects_loose.user_api.services.UserService;
import com.projects_loose.user_api.services.dtos.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    public ResponseEntity<List<UserDTO>> findAll(){
       return ResponseEntity.ok(service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable Long id) {
        UserDTO userDTO = service.getById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/{cpf}/cpf")
    public ResponseEntity<UserDTO> findUserByCpf(@PathVariable String cpf) {
        UserDTO userDTO = service.findByCpf(cpf);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/search/cpf")
    public ResponseEntity<List<UserDTO>> queryByName(@RequestParam(name="name", required = true) String name) {
        List<UserDTO> userDTO = service.queryByName(name);
        return ResponseEntity.ok(userDTO);
    }


    @PostMapping
    public ResponseEntity<UserDTO> createdUser(@RequestBody UserDTO userDTO) {
        userDTO = service.createUser(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(userDTO);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(@RequestParam Long id,@RequestBody UserDTO userDTO) {
        userDTO = service.updateUser(id,userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
