package dummygui;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        // Test Checkbox class
        Checkbox checkbox = new Checkbox("Accept Terms");
        testCheckbox(checkbox);
        testClassImplementsInterface(Checkbox.class, Clickable.class);

        // Test NormalButton class
        NormalButton normalButton = new NormalButton("Submit", 100, 50);
        testNormalButton(normalButton);
        testClassImplementsInterface(NormalButton.class, Clickable.class);
        testClassImplementsInterface(NormalButton.class, Resizable.class);
        testClassInheritance(NormalButton.class, AbstractButton.class);

        // Check if fields in Checkbox and NormalButton are private
        testFieldPrivacy(Checkbox.class);
        testFieldPrivacy(NormalButton.class);

        // Test if Clickable and Resizable are interfaces and AbstractButton is abstract
        testIfClassIsInterface(Clickable.class);
        testIfClassIsInterface(Resizable.class);
        testIfClassIsAbstract(AbstractButton.class);
    }

    private static void testCheckbox(Checkbox checkbox) {
        System.out.println("Testing Checkbox: " + checkbox);
        checkbox.click();
        System.out.println("After Click: " + checkbox);
        System.out.println();
    }

    private static void testNormalButton(NormalButton normalButton) {
        System.out.println("Testing NormalButton: " + normalButton);
        normalButton.click();
        System.out.println("After Click: " + normalButton);
        normalButton.click();
        System.out.println("After Click again: " + normalButton);
        normalButton.resize(200, 100);
        System.out.println("After Resize to (200, 100): " + normalButton);
        normalButton.resize(2);
        System.out.println("After Resize with multiplier 2: " + normalButton);
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

    private static void testIfClassIsAbstract(Class<?> clazz) {
        boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
        System.out.println(clazz.getSimpleName() + " is abstract: " + isAbstract);
        System.out.println();
    }

    private static void testClassImplementsInterface(Class<?> clazz, Class<?> interfaceClazz) {
        boolean implementsInterface = interfaceClazz.isAssignableFrom(clazz);
        System.out.println(clazz.getSimpleName() + " implements " + interfaceClazz.getSimpleName() + " interface: " + implementsInterface);
        System.out.println();
    }

    private static void testClassInheritance(Class<?> childClass, Class<?> parentClass) {
        boolean isSubclass = parentClass.isAssignableFrom(childClass);
        System.out.println(childClass.getSimpleName() + " is a subclass of " + parentClass.getSimpleName() + ": " + isSubclass);
        System.out.println();
    }
}