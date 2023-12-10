import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Select type (1 - WholeNumber, 2 - DecimalNumber): ");
        int type = sc.nextInt();

        if (type == 1) {
            System.out.print("Enter value: ");
            int value = sc.nextInt();
            WholeNumber wholeNumber = new WholeNumber(value);

            // NOTE: Uncomment the line below when you want to submit your solution already
            Tester.test(wholeNumber);

        } else if (type == 2) {
            System.out.print("Enter decimal value: ");
            int decimalValue = sc.nextInt();
            System.out.print("Enter decimal places: ");
            int decimalPlaces = sc.nextInt();
            DecimalNumber decimalNumber = new DecimalNumber(decimalValue, decimalPlaces);

            // NOTE: Uncomment the line below when you want to submit your solution already
            // debugging
            System.out.println("decimal value: " + decimalNumber.getValue());
            System.out.println("decimal places: " + decimalNumber.getDecimalPlaces());
            System.out.println(decimalNumber);

            Tester.test(decimalNumber);
        }

        sc.close();
    }
}