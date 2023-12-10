package living;

public class Main {

    public static void main(String[] args) {
         // Test Human class
        Human human = new Human();
        testLivingBeing(human, "Human");
        testClassImplementsInterface(Human.class, LivingBeing.class);

        // Test Animal class
        Animal animal = new Animal();
        testLivingBeing(animal, "Animal");
        testClassImplementsInterface(Animal.class, LivingBeing.class);

        // Test Dog class
        Dog dog = new Dog();
        testDog(dog);
        testClassImplementsInterface(Dog.class, LivingBeing.class);
        testClassInheritance(Dog.class, Animal.class);

        // Test if LivingBeing is an interface
        testIfClassIsInterface(LivingBeing.class);
    }

    private static void testLivingBeing(LivingBeing livingBeing, String className) {
        System.out.println("Testing " + className + ":");
        livingBeing.eat();
        livingBeing.grow();
        livingBeing.grow(3);
        System.out.println();
    }

    private static void testDog(Dog dog) {
        System.out.println("Testing Dog:");
        dog.eat();
        dog.grow();
        dog.grow(3);
        dog.bark();
        System.out.println();
    }

    private static void testIfClassIsInterface(Class<?> clazz) {
        boolean isInterface = clazz.isInterface();
        System.out.println(clazz.getSimpleName() + " is an interface: " + isInterface);
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