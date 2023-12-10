import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hey there, start typing your Java code here...
        Scanner sc = new Scanner(System.in);
        String name;
        int age;

        System.out.println("FATHER");
        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter age: ");
        age = sc.nextInt();
        sc.nextLine();
        Father father = new Father(name, age);
        System.out.println();

        System.out.println("SON #1");
        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter age: ");
        age = sc.nextInt();
        sc.nextLine();
        Son son1 = new Son(name, age);
        System.out.println();

        System.out.println("SON #2");
        System.out.print("Enter age: ");
        age = sc.nextInt();
        Son son2 = new Son(age);

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(father, son1, son2);

        sc.close();
    }
}