import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the Golden Retriever's gender: ");
        char gender = sc.nextLine().charAt(0);

        System.out.print("Enter the Golden Retriever's color: ");
        String color = sc.nextLine();

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(gender, color);
    }
}