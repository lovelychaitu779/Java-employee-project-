import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/** Collects and displays employee details and completed work experience. */
public class EmployeeProject {
    private static final DateTimeFormatter INPUT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DISPLAY_DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM uuuu");

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==========================================");
            System.out.println("       EMPLOYEE DETAILS COLLECTOR");
            System.out.println("==========================================");

            String name = readRequired(scanner, "Employee name: ");
            String designation = readRequired(scanner, "Employee designation: ");
            LocalDate joiningDate = readJoiningDate(scanner);
            String employeeId = readRequired(scanner, "Employee ID: ");

            Period experience = Period.between(joiningDate, LocalDate.now());

            System.out.println("\n==========================================");
            System.out.println("          EMPLOYEE DETAILS");
            System.out.println("==========================================");
            System.out.println("Employee name       : " + name);
            System.out.println("Designation         : " + designation);
            System.out.println("Joining date        : " + joiningDate.format(DISPLAY_DATE_FORMAT));
            System.out.println("Employee ID         : " + employeeId);
            System.out.printf("Experience          : %d year(s), %d month(s), %d day(s)%n",
                    experience.getYears(), experience.getMonths(), experience.getDays());
            System.out.println("==========================================");
        }
    }

    private static String readRequired(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field is required. Please try again.");
        }
    }

    private static LocalDate readJoiningDate(Scanner scanner) {
        while (true) {
            System.out.print("Employee joining date (YYYY-MM-DD): ");
            String value = scanner.nextLine().trim();
            try {
                LocalDate joiningDate = LocalDate.parse(value, INPUT_DATE_FORMAT);
                if (joiningDate.isAfter(LocalDate.now())) {
                    System.out.println("The joining date cannot be in the future.");
                    continue;
                }
                return joiningDate;
            } catch (DateTimeParseException exception) {
                System.out.println("Enter a valid date in YYYY-MM-DD format, for example 2022-06-15.");
            }
        }
    }
}
