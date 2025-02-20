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
        user.setUserDocument(encryptionService.encode(userDTO.userDocument()));
        user.setCreditCardToken(encryptionService.encode(userDTO.creditCardToken()));

        return userRepository.save(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            User userFound = user.get();
            userFound.setCreditCardToken(encryptionService.decrypt(userFound.getCreditCardToken()));
            userFound.setUserDocument(encryptionService.decrypt(userFound.getUserDocument()));
            return userFound;
        } else {
            return null;
        }
    }

}
