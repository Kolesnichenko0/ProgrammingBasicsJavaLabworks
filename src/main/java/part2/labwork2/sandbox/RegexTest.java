package part2.labwork2.sandbox;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTest {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.printf("%nВведіть номер телефону: ");
        String phone = scan.nextLine();
        Pattern p = Pattern.compile("\\+?(380-)?(67|68|96|97|98)-\\d{3}-\\d{2}-\\d{2}$");
        Matcher m = p.matcher(phone);
        if (m.matches()) {
            System.out.println("\"" + phone + "\" - правильний формат номеру");
        } else {
            System.out.println("\"" + phone + "\" - хибний формат номеру");
        }
    }
}