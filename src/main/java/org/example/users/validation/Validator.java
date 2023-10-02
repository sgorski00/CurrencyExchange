package org.example.users.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Validator {
    public boolean isValid(String word, String regex){
        Pattern patter = Pattern.compile(regex);

        if (word == null) {
            return false;
        }

        Matcher m = patter.matcher(word);
        return m.matches();
    }
}
