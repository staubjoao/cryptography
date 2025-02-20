package com.api.cryptography.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class EncryptionService implements PasswordEncoder {

    @Value("${key.value}")
    private String SECRET_KEY;

    @Override
    public String encode(CharSequence rawPassword) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedTextBytes = cipher.doFinal(rawPassword.toString().getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encryptedTextBytes);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao executar a criptografia da senha", e);
        }
    }

    @Override
    public boolean matches(CharSequence rawText, String encodedText) {
        try {
            String encodeString = encode(rawText);
            return encodeString.equals(encodedText.toString());
        } catch (Exception e) {
            throw new RuntimeException("Falha ao verificar a senha criptografada", e);
        }
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }

    public String decrypt(String encryptedText) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedTextBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            return new String(decryptedTextBytes);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar a senha", e);
        }
    }
}
