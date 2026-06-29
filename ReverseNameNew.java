import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
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

            LocalDate dob = promptDateOfBirth(scanner);
            String reversedDob = reverse(dob.toString());

            String generation = getGeneration(dob);
            String age = formatAge(dob);
            long daysUntilBirthday = daysUntilNextBirthday(dob);

            System.out.println("Reversed name: " + reversedName);
            System.out.println("Reversed Date of Birth: " + reversedDob);
            System.out.println("Generation: " + generation);
            System.out.println("Age: " + age);
            System.out.println("Days until next birthday: " + daysUntilBirthday);
        }
    }

    // Refactored: prompt helper to reduce code duplication and clarify intentions
    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    // Validates both date format and calendar validity using LocalDate parsing.
    private static LocalDate promptDateOfBirth(Scanner scanner) {
        while (true) {
            System.out.print("Enter your date of birth (YYYY-MM-DD): ");
            String dobInput = scanner.nextLine().trim();
            try {
                LocalDate dob = LocalDate.parse(dobInput);
                if (dob.isAfter(LocalDate.now())) {
                    System.out.println("Date of birth cannot be in the future.");
                    continue;
                }
                return dob;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    // Refactored: common reverse string functionality encapsulated in one method
    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    private static String formatAge(LocalDate dob) {
        Period agePeriod = Period.between(dob, LocalDate.now());
        int years = agePeriod.getYears();

        if (years > 0) {
            return years + " years";
        }

        int months = agePeriod.getMonths();
        return months + " months";
    }

    private static long daysUntilNextBirthday(LocalDate dob) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = dob.withYear(today.getYear());

        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    // Refactored: uses label constants
    public static String getGeneration(LocalDate dob) {
        try {
            int year = dob.getYear();

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