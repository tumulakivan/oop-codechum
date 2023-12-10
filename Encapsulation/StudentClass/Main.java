import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter age: ");
        int age = sc.nextInt();

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(firstName, lastName, age);

        sc.close();
    }
}