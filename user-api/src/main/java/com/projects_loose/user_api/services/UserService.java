package com.projects_loose.user_api.services;

import com.projects_loose.user_api.entities.User;
import com.projects_loose.user_api.repositories.UserRepository;
import com.projects_loose.user_api.services.dtos.UserDTO;
import com.projects_loose.user_api.services.mappers.UserMapper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

//    public Page<UserDTO> getAllPage(Pageable pageable) {
//        Page<User> users = repository.findAll(pageable);
//        return mapper.toDto(users);
//    }

    public List<UserDTO> getAllUsers() {
        List<User> users = repository.findAll();
        return mapper.toDto(users);
    }

    public UserDTO getById(long id) {
        User user = repository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.toDto(user);
    }

    public UserDTO findByCpf(String cpf) {
        User user = repository.findByCpf(cpf);
        if (Objects.nonNull(user)){
            return mapper.toDto(user);
        }
        return null;
    }

    public List<UserDTO> queryByName(String name) {
        List<User> users = repository.queryByNameLike(name);
        return mapper.toDto(users);
    }

    public UserDTO createUser(UserDTO dto) {
        dto.setRegistrationDate(LocalDateTime.now());
        User user = mapper.toEntity(dto);
        user = repository.save(user);
        return mapper.toDto(user);
    }

    public UserDTO updateUser(Long userId, UserDTO dto) {
        User user = repository.findById(userId).
                orElseThrow(() -> new RuntimeException("User not found"));

        if (Objects.nonNull(user.getEmail()) && !user.getEmail().equals(dto.getEmail())){
            user.setEmail(dto.getEmail());
        }

        if (Objects.nonNull(user.getPhoneNumber()) && !user.getPhoneNumber().equals(dto.getPhoneNumber())){
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (Objects.nonNull(user.getAddress()) && !user.getAddress().equals(dto.getAddress())){
            user.setAddress(dto.getAddress());
        }

        user = repository.save(user);
        return mapper.toDto(user);
    }

    public UserDTO deleteById(long id) {
        User user = repository.findById(id).
                orElseThrow(() -> new RuntimeException("User not found"));
        repository.delete(user);
        return mapper.toDto(user);

    }
}
