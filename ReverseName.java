import java.util.Scanner;

public class ReverseName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String reversedName = new StringBuilder(name).reverse().toString();

        String dob = getDateOfBirth(scanner);
        String reversedDob = new StringBuilder(dob).reverse().toString();

        System.out.println("Reversed name: " + reversedName);
        System.out.println("Reversed Date of Birth: " + reversedDob);
    }

    // Function to take date of birth from user
    public static String getDateOfBirth(Scanner scanner) {
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        return scanner.nextLine();
    }
}