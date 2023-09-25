package org.example.users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
        Pattern patter = Pattern.compile(regex);

        if (password == null) {
            return false;
        }

        Matcher m = patter.matcher(password);
        return m.matches();
    }
}
