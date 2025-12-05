import java.util.Scanner;

public class ReverseNameNew {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String reversedName = new StringBuilder(name).reverse().toString();

        String dob = getDateOfBirth(scanner);
        String reversedDob = new StringBuilder(dob).reverse().toString();

        String generation = getGeneration(dob);

        System.out.println("Reversed name: " + reversedName);
        System.out.println("Reversed Date of Birth: " + reversedDob);
        System.out.println("Generation: " + generation);
    }

    // Function to take date of birth from user
    public static String getDateOfBirth(Scanner scanner) {
        System.out.print("Enter your date of birth (YYYY-MM-DD): ");
        return scanner.nextLine();
    }

    // Function to determine generation by year of birth
    public static String getGeneration(String dob) {
        try {
            String yearString = dob.split("-")[0];
            int year = Integer.parseInt(yearString);

            if (year >= 1928 && year <= 1945) {
                return "Silent Generation";
            } else if (year >= 1946 && year <= 1964) {
                return "Baby Boomer";
            } else if (year >= 1965 && year <= 1980) {
                return "Generation X";
            } else if (year >= 1981 && year <= 1996) {
                return "Millennial";
            } else if (year >= 1997 && year <= 2012) {
                return "Generation Z";
            } else if (year >= 2013) {
                return "Generation Alpha";
            } else {
                return "Unknown Generation";
            }
        } catch (Exception e) {
            return "Invalid Date Format";
        }
    }
}