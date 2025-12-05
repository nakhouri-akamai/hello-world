import java.util.Scanner;

public class ReverseName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        String reversed = new StringBuilder(name).reverse().toString();
        System.out.println("Reversed name: " + reversed);
    }
}