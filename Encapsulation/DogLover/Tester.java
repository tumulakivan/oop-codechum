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

    public static void test(char gender, String color) {
        Dog dog = new Dog(gender, color);

        Scanner scanner = new Scanner(System.in);
        int operations = scanner.nextInt();

        while (operations-- > 0) {
            int code = scanner.nextInt();
            if (code == 1) {
                System.out.println(dog);
            } else if (code == 2) {
                Method getBreed = Tester.getMethod(dog, "getBreed");
                try {
                    System.out.println(getBreed.invoke(dog));
                } catch (Exception e) {
                }
            } else if (code == 3) {
                Method getGender = Tester.getMethod(dog, "getGender");
                try {
                    System.out.println(getGender.invoke(dog));
                } catch (Exception e) {
                }
            } else if (code == 4) {
                Method getColor = Tester.getMethod(dog, "getColor");
                try {
                    System.out.println(getColor.invoke(dog));
                } catch (Exception e) {
                }
            } else if (code == 5) {
                Method bark = Tester.getMethod(dog, "bark");
                try {
                    int n = scanner.nextInt();
                    bark.invoke(dog, n);
                } catch (Exception e) {
                }
            }
        }
    }
}