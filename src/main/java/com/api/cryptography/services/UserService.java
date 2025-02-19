package com.api.cryptography.services;

import com.api.cryptography.dtos.UserDTO;
import com.api.cryptography.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(UserDTO user);

    List<User> findAllUsers();

    Optional<User> findById(Long id);

}
