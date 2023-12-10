import java.util.Scanner;

public class Tester {
    public Tester() {
    }

    public static void test(Enrollment enrollment1, Enrollment enrollment2) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter operation: ");
        int n = scanner.nextInt();

        if (n == 1) {
            Enrollment newEnrollment = new Enrollment();

            if (newEnrollment.section == enrollment1.section && newEnrollment.student == enrollment1.student) {
                System.out.print("SUCCESS");
            } else {
                System.out.print("FAILED");
            }
        } else if (n == 2) {
            Enrollment newEnrollment = new Enrollment(enrollment2.section, enrollment2.student);

            if (newEnrollment.section.name == enrollment2.section.name
                    && newEnrollment.student.name == enrollment2.student.name) {
                System.out.print("SUCCESS");
            } else {
                System.out.print("FAILED");
            }
        } else if (n == 3) {
            String sectionName = "F1";
            String studentName = "David";

            Section testSection = new Section(sectionName);
            Student testStudent = new Student(studentName);

            Enrollment testEnrollment = new Enrollment(testSection, testStudent);

            if (testEnrollment.section.name == sectionName && testEnrollment.student.name == studentName) {
                System.out.print("SUCCESS");
            } else {
                System.out.print("FAILED");
            }
        } else {
            Enrollment testEnrollment = new Enrollment();

            if (testEnrollment.section == null && testEnrollment.student == null) {
                System.out.print("SUCCESS");
            } else {
                System.out.print("FAILED");
            }
        }
    }
}