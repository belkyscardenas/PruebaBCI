package cl.usuarios.util;

import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final Pattern emailPattern = Pattern.compile("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b");
    private static final Pattern passwordPattern = Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,}$");

    public static boolean validarEmail(String email) {

        return emailPattern.matcher(email).find();
    }

    public static boolean validarPassword(String password) {

        return passwordPattern.matcher(password).find();
    }
}