import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class Tester {
    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    private static Field getField(Object obj, String fieldName) {
        List<Field> fields = getAllFields(new LinkedList<Field>(), obj.getClass());
        for (Field f : fields) {
            if (f.getName().equals(fieldName)) {
                return f;
            }
        }
        return null;
    }

    private static Method getMethod(Object obj, String methodName) {
        Method[] methods = obj.getClass().getMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }

    public static void test(Dog dog) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();

        if (code == 1) {
            Field breed = Tester.getField(dog, "breed");
            Field barkCount = Tester.getField(dog, "barkCount");

            if ((breed != null && Modifier.isPrivate(breed.getModifiers())) &&
                    (barkCount != null && Modifier.isPrivate(barkCount.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getBreed = Tester.getMethod(dog, "getBreed");
            Method setBreed = Tester.getMethod(dog, "setBreed");

            try {
                getBreed.invoke(dog);
                setBreed.invoke(dog, "Test");
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            Method hasBarkedALot = Tester.getMethod(dog, "hasBarkedALot");

            try {
                hasBarkedALot.invoke(dog);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 4) {
            Method bark = Tester.getMethod(dog, "bark");

            try {
                bark.invoke(dog, 10);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 5) {
            String testBreed = "Test";
            int testBarkCount = 0;
            Dog newDog = new Dog();

            Method setBreed = Tester.getMethod(newDog, "setBreed");

            try {
                setBreed.invoke(newDog, testBreed);
                Field breed = Tester.getField(newDog, "breed");
                Field barkCount = Tester.getField(newDog, "barkCount");

                if (breed != null) {
                    breed.setAccessible(true);
                    Object value = breed.get(newDog);
                    if (!(value.toString() == testBreed)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (barkCount != null) {
                    barkCount.setAccessible(true);
                    Object value = barkCount.get(newDog);
                    if (!(Integer.parseInt(value.toString()) == testBarkCount)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED  " + e);
            }
        } else if (code == 6) {
            String testBreed = "Test";
            Dog newDog = new Dog();

            Method getBreed = Tester.getMethod(newDog, "getBreed");
            Method setBreed = Tester.getMethod(newDog, "setBreed");

            try {
                setBreed.invoke(newDog, testBreed);
                String breed = getBreed.invoke(newDog).toString();

                if (!(breed.equals(testBreed))) {
                    System.out.println("FAILED1");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED2");
            }
        } else if (code == 7) {
            int testBarkCount = 5;
            Dog newDog = new Dog();

            Method bark = Tester.getMethod(newDog, "bark");

            try {
                bark.invoke(newDog, testBarkCount);
                Field barkCountField = Tester.getField(newDog, "barkCount");
                barkCountField.setAccessible(true);
                Object value = barkCountField.get(newDog);
                int barkCount = Integer.parseInt(value.toString());

                if (!(barkCount == testBarkCount)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 8) {
            boolean testHasBarkedALot = true;
            Dog newDog = new Dog();

            Method bark = Tester.getMethod(newDog, "bark");
            Method hasBarkedALot = Tester.getMethod(newDog, "hasBarkedALot");

            try {
                bark.invoke(newDog, 101);
                boolean hasBarkedALotBool = Boolean.parseBoolean(hasBarkedALot.invoke(newDog).toString());

                if (!(hasBarkedALotBool == testHasBarkedALot)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 9) {
            String testBreed = "Test";
            int testBarkCount = 5;
            boolean testHasBarkedALot = false;
            Dog newDog = new Dog();

            Method getBreed = Tester.getMethod(newDog, "getBreed");
            Method setBreed = Tester.getMethod(newDog, "setBreed");

            Method bark = Tester.getMethod(newDog, "bark");
            Method hasBarkedALot = Tester.getMethod(newDog, "hasBarkedALot");

            try {
                setBreed.invoke(newDog, testBreed);
                bark.invoke(newDog, testBarkCount);

                Field barkCountField = Tester.getField(newDog, "barkCount");
                barkCountField.setAccessible(true);
                Object value = barkCountField.get(newDog);

                String breed = getBreed.invoke(newDog).toString();
                int barkCount = Integer.parseInt(value.toString());
                boolean hasBarkedALotBool = Boolean.parseBoolean(hasBarkedALot.invoke(newDog).toString());

                if (!(breed.equals(testBreed))) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(barkCount == testBarkCount)) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(hasBarkedALotBool == testHasBarkedALot)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}