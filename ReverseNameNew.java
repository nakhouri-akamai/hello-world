import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * A small console application that accepts a user's name and date of birth,
 * then prints the reversed name, reversed birth date, generation label, age,
 * and number of days until the next birthday.
 */
public class ReverseNameNew {

    /**
     * Label used for people born in the Silent Generation.
     */
    private static final String SILENT = "Silent Generation";

    /**
     * Label used for people born in the Baby Boomer generation.
     */
    private static final String BOOMER = "Baby Boomer";

    /**
     * Label used for people born in Generation X.
     */
    private static final String GEN_X = "Generation X";

    /**
     * Label used for people born in the Millennial generation.
     */
    private static final String MILLENNIAL = "Millennial";

    /**
     * Label used for people born in Generation Z.
     */
    private static final String GEN_Z = "Generation Z";

    /**
     * Label used for people born in Generation Alpha.
     */
    private static final String GEN_ALPHA = "Generation Alpha";

    /**
     * Fallback label used when a birth year does not match any known generation.
     */
    private static final String UNKNOWN = "Unknown Generation";

    /**
     * Fallback response used when date parsing fails unexpectedly.
     */
    private static final String INVALID_FORMAT = "Invalid Date Format";

    /**
     * Program entry point.
     *
     * @param args command-line arguments passed to the application
     */
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

    /**
     * Displays a prompt message and returns the user's input.
     *
     * @param scanner scanner used to read console input
     * @param message message shown to the user
     * @return the text entered by the user
     */
    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Repeatedly asks the user for a date of birth until a valid, non-future date is entered.
     * <p>
     * The method uses {@link LocalDate#parse(CharSequence)} to validate both the format
     * and the calendar correctness of the input.
     *
     * @param scanner scanner used to read console input
     * @return a valid date of birth that is not in the future
     */
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

    /**
     * Reverses the supplied string.
     *
     * @param input the text to reverse
     * @return the reversed text
     */
    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Formats the user's age as a readable string.
     * <p>
     * If the person has at least one full year of age, the value is returned in years.
     * Otherwise, the value is returned in months.
     *
     * @param dob date of birth used to calculate age
     * @return age as a human-readable string
     */
    private static String formatAge(LocalDate dob) {
        Period agePeriod = Period.between(dob, LocalDate.now());
        int years = agePeriod.getYears();

        if (years > 0) {
            return years + " years";
        }

        int months = agePeriod.getMonths();
        return months + " months";
    }

    /**
     * Calculates the number of days remaining until the user's next birthday.
     * <p>
     * The method first checks the birthday in the current year. If that date has already
     * passed, it advances the birthday to the next year.
     *
     * @param dob date of birth used to compute the next birthday
     * @return number of days until the next birthday
     */
    private static long daysUntilNextBirthday(LocalDate dob) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = dob.withYear(today.getYear());

        // If the birthday already happened this year, move to the next year.
        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    /**
     * Determines the generation associated with a birth year.
     *
     * @param dob date of birth used to determine the generation
     * @return generation label for the supplied date of birth
     */
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
