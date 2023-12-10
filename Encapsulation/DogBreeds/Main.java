import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hey there, start typing your Java code here...
        Scanner sc = new Scanner(System.in);
        Dog dog = new Dog();

        System.out.print("Enter dog's breed: ");
        String breed = sc.nextLine();
        dog.setBreed(breed);
        System.out.print("Enter n: ");
        int n = sc.nextInt();
        dog.bark(n);
        if (dog.hasBarkedALot())
            System.out.println("Dog has barked a lot!");
        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(dog);
    }
}