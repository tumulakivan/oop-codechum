import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Enrollment enrollment1 = new Enrollment();

        System.out.print("Enter section name: ");
        String secName = sc.nextLine();
        Section section = new Section(secName);
        System.out.print("Enter student name: ");
        String stName = sc.nextLine();
        Student student = new Student(stName);

        Enrollment enrollment2 = new Enrollment(section, student);
        Tester.test(enrollment1, enrollment2);
        sc.close();
    }
}
