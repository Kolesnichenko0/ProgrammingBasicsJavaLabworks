package part2.labwork2.third;

import java.util.Scanner;

public class PhoneInput {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Kyivstar phone number: ");
        String phone = scan.nextLine();

        PhoneValidator phoneValidator = new PhoneValidator();

        if (phoneValidator.isValidPhone(phone)) {
            System.out.println("\"" + phone + "\" - correct number format");
        } else {
            System.out.println("\"" + phone + "\" - incorrect number format");
        }
    }
}
