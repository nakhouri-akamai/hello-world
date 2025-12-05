import java.util.Scanner;

public class ReverseNameNew {

    // Generation Label Constants (Refactored: moved string literals to constants)
    private static final String SILENT = "Silent Generation";
    private static final String BOOMER = "Baby Boomer";
    private static final String GEN_X = "Generation X";
    private static final String MILLENNIAL = "Millennial";
    private static final String GEN_Z = "Generation Z";
    private static final String GEN_ALPHA = "Generation Alpha";
    private static final String UNKNOWN = "Unknown Generation";
    private static final String INVALID_FORMAT = "Invalid Date Format";

    public static void main(String[] args) {
        // Refactored: Use try-with-resources for automatic resource management (Scanner closed automatically)
        try (Scanner scanner = new Scanner(System.in)) {
            // Refactored: Use prompt helper function
            String name = prompt(scanner, "Enter your name: ");
            // Refactored: Use reusable reverse function
            String reversedName = reverse(name);

            // Refactored: Use validated date of birth input helper
            String dob = promptDateOfBirth(scanner);
            String reversedDob = reverse(dob);

            String generation = getGeneration(dob);

            System.out.println("Reversed name: " + reversedName);
            System.out.println("Reversed Date of Birth: " + reversedDob);
            System.out.println("Generation: " + generation);
        }
    }

    // Refactored: prompt helper to reduce code duplication and clarify intentions
    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    // Refactored: loop to validate date format on input
    private static String promptDateOfBirth(Scanner scanner) {
        while (true) {
            System.out.print("Enter your date of birth (YYYY-MM-DD): ");
            String dob = scanner.nextLine();
            if (dob.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return dob;
            }
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    // Refactored: common reverse string functionality encapsulated in one method
    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // Refactored: uses label constants
    public static String getGeneration(String dob) {
        try {
            String yearString = dob.split("-")[0];
            int year = Integer.parseInt(yearString);

            if (year >= 1928 && year <= 1945) {
                return SILENT;
            } else if (year >= 1946 && year <= 1964) {
                return BOOMER;
            } else if (year >= 1965 && year <= 1980) {
                return GEN_X;
            } else if (year >= 1981 && year <= 1996) {
                return MILLENNIAL;
            } else if (year >= 1997 && year <= 2012) {
                return GEN_Z;
            } else if (year >= 2013) {
                return GEN_ALPHA;
            } else {
                return UNKNOWN;
            }
        } catch (Exception e) {
            return INVALID_FORMAT;
        }
    }
}