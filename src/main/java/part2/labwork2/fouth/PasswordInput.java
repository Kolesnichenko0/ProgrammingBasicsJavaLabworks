package part2.labwork2.fouth;

import java.util.Scanner;

public class PasswordInput {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the password: ");
        String password = scan.nextLine();

        PasswordValidator passwordValidator = new PasswordValidator();

        if (passwordValidator.isValidPassword(password)) {
            System.out.println("\"" + password + "\" - correct password");
        } else {
            System.out.println("\"" + password + "\" - incorrect password");
        }
    }
}