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

    public static void test(String color, double price, char size) {
        Car car = new Car(color, price, size);

        Scanner scanner = new Scanner(System.in);
        int operations = scanner.nextInt();

        while (operations-- > 0) {
            int code = scanner.nextInt();

            if (code == 1) {
                System.out.println(car);
            } else if (code == 2) {
                Method getColor = Tester.getMethod(car, "getColor");
                try {
                    System.out.println(getColor.invoke(car));
                } catch (Exception e) {
                }
            } else if (code == 3) {
                Method getPrice = Tester.getMethod(car, "getPrice");
                try {
                    System.out.println(getPrice.invoke(car));
                } catch (Exception e) {
                }
            } else if (code == 4) {
                Method getSize = Tester.getMethod(car, "getSize");
                try {
                    System.out.println(getSize.invoke(car));
                } catch (Exception e) {
                }
            } else if (code == 5) {
                Method setColor = Tester.getMethod(car, "setColor");
                try {
                    String newColor = scanner.next();
                    setColor.invoke(car, newColor);
                } catch (Exception e) {
                    System.out.print(e.toString());
                }
            }
        }
    }
}