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

    public static void test(Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();

        if (code == 1) {
            Field name = Tester.getField(person, "name");
            Field age = Tester.getField(person, "age");
            Field gender = Tester.getField(person, "gender");

            if ((name != null && Modifier.isPrivate(name.getModifiers())) &&
                    (age != null && Modifier.isPrivate(age.getModifiers())) &&
                    (gender != null && Modifier.isPrivate(gender.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getName = Tester.getMethod(person, "getName");
            Method getAge = Tester.getMethod(person, "getAge");
            Method getGender = Tester.getMethod(person, "getGender");

            try {
                getName.invoke(person);
                getAge.invoke(person);
                getGender.invoke(person);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            Method setName = Tester.getMethod(person, "setName");
            Method setAge = Tester.getMethod(person, "setAge");
            Method setGender = Tester.getMethod(person, "setGender");

            try {
                setName.invoke(person, "Test");
                setAge.invoke(person, 5);
                setGender.invoke(person, 'M');
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 4) {
            String testName = "Test";
            int testAge = 5;
            char testGender = 'F';
            Person newPerson = new Person();

            Method setName = Tester.getMethod(newPerson, "setName");
            Method setAge = Tester.getMethod(newPerson, "setAge");
            Method setGender = Tester.getMethod(newPerson, "setGender");

            try {
                setName.invoke(newPerson, testName);
                setAge.invoke(newPerson, testAge);
                setGender.invoke(newPerson, testGender);

                Field name = Tester.getField(newPerson, "name");
                Field age = Tester.getField(newPerson, "age");
                Field gender = Tester.getField(newPerson, "gender");
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(newPerson);
                    if (!(value.toString() == testName)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (age != null) {
                    age.setAccessible(true);
                    Object value = age.get(newPerson);
                    if (!(Integer.parseInt(value.toString()) == testAge)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (gender != null) {
                    gender.setAccessible(true);
                    Object value = gender.get(newPerson);
                    if (!(value.toString().charAt(0) == testGender)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 5) {
            String testName = "Test";
            Person newPerson = new Person();

            Method getName = Tester.getMethod(newPerson, "getName");
            Method setName = Tester.getMethod(newPerson, "setName");

            try {
                setName.invoke(newPerson, testName);
                String name = getName.invoke(newPerson).toString();

                if (!(name == testName)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 6) {
            int testAge = 5;
            Person newPerson = new Person();

            Method getAge = Tester.getMethod(newPerson, "getAge");
            Method setAge = Tester.getMethod(newPerson, "setAge");

            try {
                setAge.invoke(newPerson, testAge);
                int age = Integer.parseInt(getAge.invoke(newPerson).toString());

                if (!(age == testAge)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 7) {
            char testGender = 'F';
            Person newPerson = new Person();

            Method getGender = Tester.getMethod(newPerson, "getGender");
            Method setGender = Tester.getMethod(newPerson, "setGender");

            try {
                setGender.invoke(newPerson, testGender);
                char gender = getGender.invoke(newPerson).toString().charAt(0);

                if (!(gender == testGender)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 8) {
            String testName = "Test";
            int testAge = 5;
            char testGender = 'F';
            Person newPerson = new Person();

            Method getName = Tester.getMethod(newPerson, "getName");
            Method getAge = Tester.getMethod(newPerson, "getAge");
            Method getGender = Tester.getMethod(newPerson, "getGender");

            Method setName = Tester.getMethod(newPerson, "setName");
            Method setAge = Tester.getMethod(newPerson, "setAge");
            Method setGender = Tester.getMethod(newPerson, "setGender");

            try {
                setName.invoke(newPerson, testName);
                setAge.invoke(newPerson, testAge);
                setGender.invoke(newPerson, testGender);

                String name = getName.invoke(newPerson).toString();
                int age = Integer.parseInt(getAge.invoke(newPerson).toString());
                char gender = getGender.invoke(newPerson).toString().charAt(0);

                if (!(name == testName)) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(age == testAge)) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(gender == testGender)) {
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