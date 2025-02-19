package com.api.cryptography.services.impl;

import com.api.cryptography.dtos.UserDTO;
import com.api.cryptography.models.User;
import com.api.cryptography.repositoris.UserRepository;
import com.api.cryptography.services.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;

    public UserServiceImpl(UserRepository userRepository, EncryptionService encryptionService) {
        this.userRepository = userRepository;
        this.encryptionService = encryptionService;
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        User user = new User();

        user.setValue(userDTO.value());
        user.setUserDocument(encryptionService.encryptString(userDTO.userDocument()));
        user.setCreditCardToken(encryptionService.encryptString(userDTO.creditCardToken()));

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

}
