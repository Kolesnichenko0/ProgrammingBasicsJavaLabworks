package part2.labwork2.third;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneValidator {
    public static final String PHONE_PATTERN = "^(\\+380|380|0)?(67|68|96|97|98)(-)?\\d{3}(-)?\\d{2}(-)?\\d{2}$";

    public boolean isValidPhone(String phoneString) {
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phoneString);
        return matcher.matches();
    }
}
