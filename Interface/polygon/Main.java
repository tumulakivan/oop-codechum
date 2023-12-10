package polygon;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
         // Test Rectangle with multiple test cases
        testPolygon("Rectangle", new Rectangle(5.0, 3.0));
        testPolygon("Rectangle", new Rectangle(10.0, 2.0));

        // Test Square with multiple test cases
        testPolygon("Square", new Square(4.0));
        testPolygon("Square", new Square(7.0));

        // Test Trapezoid with multiple test cases
        testPolygon("Trapezoid", new Trapezoid(3.0, 4.0, 5.0, 6.0, 4.0));
        testPolygon("Trapezoid", new Trapezoid(2.0, 3.0, 4.0, 5.0, 3.5));

        // Check if fields in each class are private
        testFieldPrivacy(Rectangle.class);
        testFieldPrivacy(Square.class);
        testFieldPrivacy(Trapezoid.class);

        // Test if PolygonInterface is an interface
        testIfClassIsInterface(PolygonInterface.class);
    }

    private static void testPolygon(String className, PolygonInterface polygon) {
        System.out.println("Testing " + className + ":");
        System.out.println("Area: " + polygon.getArea());
        System.out.println("Perimeter: " + polygon.getPerimeter());
        System.out.println();
    }

    private static void testFieldPrivacy(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        boolean allFieldsPrivate = true;

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                allFieldsPrivate = false;
                System.out.println("Field '" + field.getName() + "' in " + clazz.getSimpleName() + " is not private");
            }
        }

        if (allFieldsPrivate) {
            System.out.println("All fields in " + clazz.getSimpleName() + " are private");
        } else {
            System.out.println("Not all fields in " + clazz.getSimpleName() + " are private");
        }
        System.out.println();
    }

    private static void testIfClassIsInterface(Class<?> clazz) {
        boolean isInterface = clazz.isInterface();
        System.out.println(clazz.getSimpleName() + " is an interface: " + isInterface);
        System.out.println();
    }
}