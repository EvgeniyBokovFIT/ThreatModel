package ru.nsu.threatmodel.utils;

public class PasswordUtils {
    private PasswordUtils() {

    }

    public static boolean isEnteredPasswordMatchesUserPassword(
            String enteredPassword, String password, String salt) {
        var hashedPassword = PasswordGenerationUtils
                .hashPassword(enteredPassword, salt);

        return password.equals(hashedPassword);
    }
}
