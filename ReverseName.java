import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ReverseName {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = readName(scanner);
            String dob = readDateOfBirth(scanner);

            System.out.println("Reversed name: " + reverse(name));
            System.out.println("Reversed Date of Birth: " + reverse(dob));
        }
    }

    private static String readName(Scanner scanner) {
        System.out.print("Enter your name: ");
        return scanner.nextLine().trim();
    }

    private static String readDateOfBirth(Scanner scanner) {
        while (true) {
            System.out.print("Enter your date of birth (YYYY-MM-DD): ");
            String input = scanner.nextLine().trim();

            try {
                LocalDate.parse(input);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    private static String reverse(String value) {
        return new StringBuilder(value).reverse().toString();
    }
}
