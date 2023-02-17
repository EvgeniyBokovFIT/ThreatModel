package ru.nsu.threatmodel.utils;

import org.springframework.util.Base64Utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class PasswordGenerationUtils {
    private static final Integer SALT_SIZE = 32;
    private static final String PBE_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final Integer PBE_NUMBER_OF_ITERATIONS = 1000;
    private static final Integer HASH_PASSWORD_SIZE = 64 * 8;

    private PasswordGenerationUtils() {

    }

    public static String generateSalt() {
        var salt = new byte[SALT_SIZE];
        SecureRandom secureRandom;
        try {
            secureRandom = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        secureRandom.nextBytes(salt);
        return Base64Utils.encodeToString(salt);
    }

    public static String hashPassword(String password, String salt) {
        var spec = new PBEKeySpec(password.toCharArray(),
                Base64Utils.decodeFromString(salt), PBE_NUMBER_OF_ITERATIONS, HASH_PASSWORD_SIZE);
        byte[] hashedPassword;
        try {
            hashedPassword = SecretKeyFactory.getInstance(PBE_ALGORITHM).generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return Base64Utils.encodeToString(hashedPassword);
    }


}
