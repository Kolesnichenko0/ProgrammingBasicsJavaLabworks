package part2.labwork2.fouth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    public static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[_\\-*])[A-Za-z\\d_\\-*]{6,32}$";

    public boolean isValidPassword(String passwordString) {
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordString);
        return matcher.matches();
    }
}
