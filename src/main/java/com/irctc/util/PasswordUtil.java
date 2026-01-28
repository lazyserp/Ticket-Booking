package com.irctc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtil {

    // We use a simple SHA-256 hash for demonstration purposes.
    // In a real production app, use PBKDF2 or Argon2.
    public static String hashPassword(String plainTextPassword) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(plainTextPassword.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword) {
        String newHash = hashPassword(plainTextPassword);
        return newHash.equals(hashedPassword);
    }
}
