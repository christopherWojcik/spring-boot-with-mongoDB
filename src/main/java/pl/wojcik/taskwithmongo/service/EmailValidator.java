package pl.wojcik.taskwithmongo.service;



import java.util.regex.Pattern;

public class EmailValidator {

    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

    protected static boolean validate(String email) {
        if (email == null){
            throw new NullPointerException("Email should not be null!");
        }
        return Pattern.compile(REGEX).matcher(email).matches();
    }

}
