package com.api.cryptography.services.impl;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    private final StringEncryptor encryptor;

    public EncryptionService(StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public String encryptString(String value) {
        return encryptor.encrypt(value);
    }

    public String decryptString(String value) {
        return encryptor.decrypt(value);
    }
}
