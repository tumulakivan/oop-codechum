import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter constructor type: ");
        int constructorType = sc.nextInt();
        int amount = 0;
        String denomination = null;
        Money money = null;

        if (constructorType == 2) {
            System.out.print("Enter amount: ");
            amount = sc.nextInt();
            money = new Money(amount);
        } else if (constructorType == 3) {
            System.out.print("Enter amount: ");
            amount = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter denomination: ");
            denomination = sc.nextLine();
            money = new Money(amount, denomination);
        }
        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(money, constructorType);

        sc.close();
    }
}