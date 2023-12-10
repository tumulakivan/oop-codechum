import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hey there, start typing your Java code here...
        Scanner sc = new Scanner(System.in);
        String name;
        char gender;
        int age;

        System.out.print("Enter name: ");
        name = sc.nextLine();
        System.out.print("Enter age: ");
        age = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter gender: ");
        gender = sc.next().charAt(0);

        Person person = new Person();
        person.setName(name);
        person.setAge(age);
        person.setGender(gender);

        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Gender: " + person.getGender());
        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(person);
        sc.close();
    }
}