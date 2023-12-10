import java.util.*;

public class Main {
    public static void main(String args[]) {
        int a, b;
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        a = sc.nextInt();
        System.out.print("Enter value of b: ");
        b = sc.nextInt();
        HypotheticalNonAbstract hypotheticalNonAbstract = new HypotheticalNonAbstract(a, b);

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(hypotheticalNonAbstract);
    }
}