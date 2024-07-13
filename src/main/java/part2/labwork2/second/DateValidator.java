package part2.labwork2.second;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    public static final String DATE_PATTERN = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$";

    public boolean isValidDate(String dateString) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(dateString);
        return matcher.matches();
    }
}
