import java.lang.reflect.Method;
import java.util.Scanner;

class Tester {
    private static Method getMethod(Object obj, String methodName) {
        Method[] methods = obj.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }

    public static void test(String firstName, String lastName, int age) {
        Student student = new Student(firstName, lastName, age);

        Scanner scanner = new Scanner(System.in);
        int operations = scanner.nextInt();

        while (operations-- > 0) {
            int code = scanner.nextInt();

            if (code == 1) {
                Method getName = Tester.getMethod(student, "getName");
                try {
                    System.out.println(getName.invoke(student));
                } catch (Exception e) {
                }
            } else if (code == 2) {
                Method increaseAge = Tester.getMethod(student, "increaseAge");
                try {
                    increaseAge.invoke(student);
                } catch (Exception e) {
                }
            } else if (code == 3) {
                System.out.println(student);
            }
        }
    }
}