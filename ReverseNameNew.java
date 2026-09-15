import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class ReverseNameNew {

    // Generation labels are stored as constants so the text is defined in one place.
    // This makes the code easier to maintain if the wording ever needs to change.
    private static final String SILENT = "Silent Generation";
    private static final String BOOMER = "Baby Boomer";
    private static final String GEN_X = "Generation X";
    private static final String MILLENNIAL = "Millennial";
    private static final String GEN_Z = "Generation Z";
    private static final String GEN_ALPHA = "Generation Alpha";
    private static final String UNKNOWN = "Unknown Generation";
    private static final String INVALID_FORMAT = "Invalid Date Format";

    public static void main(String[] args) {
        // Try-with-resources automatically closes the Scanner when the program ends.
        try (Scanner scanner = new Scanner(System.in)) {
            // Ask the user for their name and reverse the text.
            String name = prompt(scanner, "Enter your name: ");
            String reversedName = reverse(name);

            // Read and validate the date of birth before using it in the program.
            LocalDate dob = promptDateOfBirth(scanner);
            String reversedDob = reverse(dob.toString());

            // Use the date of birth to calculate several pieces of information.
            String generation = getGeneration(dob);
            String age = formatAge(dob);
            long daysUntilBirthday = daysUntilNextBirthday(dob);

            // Print the results in a simple, user-friendly format.
            System.out.println("Reversed name: " + reversedName);
            System.out.println("Reversed Date of Birth: " + reversedDob);
            System.out.println("Generation: " + generation);
            System.out.println("Age: " + age);
            System.out.println("Days until next birthday: " + daysUntilBirthday);
        }
    }

    // Helper method to display a message and return the user's input.
    // This avoids repeating the same prompt-and-read pattern in multiple places.
    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    // Continuously asks for a date of birth until the user enters a valid date.
    // LocalDate.parse() checks both the format and the calendar validity.
    // For example, it rejects values like 2026-02-30 and also rejects invalid text.
    private static LocalDate promptDateOfBirth(Scanner scanner) {
        while (true) {
            System.out.print("Enter your date of birth (YYYY-MM-DD): ");
            String dobInput = scanner.nextLine().trim();
            try {
                LocalDate dob = LocalDate.parse(dobInput);

                // Prevent future dates because a birth date must be today or earlier.
                if (dob.isAfter(LocalDate.now())) {
                    System.out.println("Date of birth cannot be in the future.");
                    continue;
                }
                return dob;
            } catch (DateTimeParseException e) {
                // Show a clear message when the input cannot be parsed as a date.
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    // Reverses any string by using StringBuilder's built-in reverse() method.
    // This works for both the name and the date string.
    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    // Estimates the user's age using the difference between the birth date and today.
    // If the person has at least one full year, the result is shown in years.
    // Otherwise, it falls back to months for a simpler summary.
    private static String formatAge(LocalDate dob) {
        Period agePeriod = Period.between(dob, LocalDate.now());
        int years = agePeriod.getYears();

        if (years > 0) {
            return years + " years";
        }

        int months = agePeriod.getMonths();
        return months + " months";
    }

    // Calculates how many days are left until the next birthday.
    // First it uses the current year's birthday, then moves to next year if needed.
    private static long daysUntilNextBirthday(LocalDate dob) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = dob.withYear(today.getYear());

        // If the birthday already happened this year, move to the next year.
        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    // Determines the generation based on the birth year.
    // The year ranges are hard-coded because generation labels are usually defined by date ranges.
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
            // Returns a safe fallback value if anything unexpected happens.
            return INVALID_FORMAT;
        }
    }
}
