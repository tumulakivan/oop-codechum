import java.util.Scanner;

class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the car's color: ");
        String color = sc.nextLine();

        System.out.print("Enter the car's price: ");
        double price = sc.nextDouble();

        System.out.print("Enter the car's size: ");
        char size = sc.next().charAt(0);

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(color, price, size);

        sc.close();
    }
}