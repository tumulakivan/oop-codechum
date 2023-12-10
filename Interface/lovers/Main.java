package lovers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        // Creating instances
        MaleFriend maleFriend = new MaleFriend();
        FemaleFriend femaleFriend = new FemaleFriend();

        // Establishing relationships
        maleFriend.setLover(femaleFriend);
        femaleFriend.setLover(maleFriend);

        // Testing interactions
        testFriend(maleFriend, femaleFriend);
        testFriend(femaleFriend, maleFriend);

        // Check if fields in Friend, MaleFriend, and FemaleFriend are private
        testFieldPrivacy(Friend.class);
        testFieldPrivacy(MaleFriend.class);
        testFieldPrivacy(FemaleFriend.class);

        // Test if classes are interfaces or abstract
        testIfClassIsInterface(BoyFriend.class);
        testIfClassIsInterface(GirlFriend.class);
        testIfClassIsInterface(Lover.class);
        testIfClassIsAbstract(Friend.class);

        // Test inheritance and interface implementation
        testClassInheritance(MaleFriend.class, Friend.class);
        testClassImplementsInterface(MaleFriend.class, BoyFriend.class);
        testClassImplementsInterface(MaleFriend.class, Lover.class);

        testClassInheritance(FemaleFriend.class, Friend.class);
        testClassImplementsInterface(FemaleFriend.class, GirlFriend.class);
        testClassImplementsInterface(FemaleFriend.class, Lover.class);
    }
    private static void testFriend(MaleFriend maleFriend, FemaleFriend femaleFriend) {
        System.out.println("Testing MaleFriend:");
        maleFriend.giveLove();
        maleFriend.giveFlowers(5);
        System.out.println("After interactions: " + maleFriend + " & " + femaleFriend);
        System.out.println();
    }

    private static void testFriend(FemaleFriend femaleFriend, MaleFriend maleFriend) {
        System.out.println("Testing FemaleFriend:");
        femaleFriend.giveLove();
        System.out.println("After interactions: " + femaleFriend + " & " + maleFriend);
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

    private static void testClassInheritance(Class<?> childClass, Class<?> parentClass) {
        boolean isSubclass = parentClass.isAssignableFrom(childClass);
        System.out.println(childClass.getSimpleName() + " is a subclass of " + parentClass.getSimpleName() + ": " + isSubclass);
        System.out.println();
    }

    private static void testClassImplementsInterface(Class<?> clazz, Class<?> interfaceClazz) {
        boolean implementsInterface = interfaceClazz.isAssignableFrom(clazz);
        System.out.println(clazz.getSimpleName() + " implements " + interfaceClazz.getSimpleName() + " interface: " + implementsInterface);
        System.out.println();
    }
}