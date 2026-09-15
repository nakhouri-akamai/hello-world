import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * Console application that reads a user's name and date of birth, then prints
 * the reversed name, reversed date of birth, generation label, age, and days
 * until the next birthday.
 */
public class ReverseNameNew {

    /**
     * Generation label for people born in the Silent Generation.
     */
    private static final String SILENT = "Silent Generation";

    /**
     * Generation label for people born in the Baby Boomer generation.
     */
    private static final String BOOMER = "Baby Boomer";

    /**
     * Generation label for people born in Generation X.
     */
    private static final String GEN_X = "Generation X";

    /**
     * Generation label for people born in the Millennial generation.
     */
    private static final String MILLENNIAL = "Millennial";

    /**
     * Generation label for people born in Generation Z.
     */
    private static final String GEN_Z = "Generation Z";

    /**
     * Generation label for people born in Generation Alpha.
     */
    private static final String GEN_ALPHA = "Generation Alpha";

    /**
     * Default generation label used when a birth year does not match a known range.
     */
    private static final String UNKNOWN = "Unknown Generation";

    /**
     * Fallback message returned when a date cannot be processed as expected.
     */
    private static final String INVALID_FORMAT = "Invalid Date Format";

    /**
     * Application entry point.
     *
     * @param args command-line arguments passed to the program
     */
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String name = prompt(scanner, "Enter your name: ");
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

    /**
     * Displays a prompt and returns the user's input.
     *
     * @param scanner scanner used to read console input
     * @param message message displayed to the user
     * @return the entered text
     */
    private static String prompt(Scanner scanner, String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    /**
     * Prompts the user for a date of birth until a valid non-future date is entered.
     * <p>
     * Input is validated using {@link LocalDate#parse(CharSequence)} to ensure both
     * formatting and calendar correctness.
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

    /**
     * Reverses the supplied string.
     *
     * @param input string to reverse
     * @return the reversed string
     */
    private static String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    /**
     * Formats the user's age as a readable string.
     * <p>
     * If the user is at least one year old, the age is returned in years.
     * Otherwise, the age is returned in months.
     *
     * @param dob date of birth used to calculate age
     * @return a human-readable age string
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
     * Calculates the number of days until the user's next birthday.
     * <p>
     * The birthday is first computed for the current year. If that date has already
     * passed, the calculation rolls forward to the next year.
     *
     * @param dob date of birth used to calculate the next birthday
     * @return number of days until the next birthday
     */
    private static long daysUntilNextBirthday(LocalDate dob) {
        LocalDate today = LocalDate.now();
        LocalDate nextBirthday = dob.withYear(today.getYear());

        if (nextBirthday.isBefore(today)) {
            nextBirthday = nextBirthday.plusYears(1);
        }

        return ChronoUnit.DAYS.between(today, nextBirthday);
    }

    /**
     * Determines the generation associated with the supplied date of birth.
     *
     * @param dob date of birth used to determine generation
     * @return generation label matching the birth year
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
            return INVALID_FORMAT;
        }
    }
}
