package org.example.users.validation;

public class LoginValidation extends Validator{
    private static final String REGEX = "^[a-z0-9]+([a-z0-9]*|[._-]?[a-z0-9]+)*.{4,20}$";
    public boolean isValid(String word) {
        return super.isValid(word, REGEX);
    }
}
