package org.example.users.validation;

public class PasswordValidation extends Validator{
    private static final String REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    public boolean isValid(String word) {
        return super.isValid(word, REGEX);
    }
}
