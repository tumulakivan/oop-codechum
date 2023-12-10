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

    public static void test(Father father, Son son1, Son son2) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of operations: ");
        int operations = scanner.nextInt();

        while (operations-- > 0) {
            System.out.print("Enter code: ");
            int code = scanner.nextInt();
            if (code == 1) {
                Field name = Tester.getField(father, "name");
                Field age = Tester.getField(father, "age");
                Field gender = Tester.getField(father, "gender");

                if ((name != null && Modifier.isPrivate(name.getModifiers())) &&
                        (age != null && Modifier.isPrivate(age.getModifiers())) &&
                        (gender != null && Modifier.isPrivate(gender.getModifiers()))) {
                    System.out.println("Fields are all private");
                }
            } else if (code == 2) {
                Method getName = Tester.getMethod(father, "getName");
                Method getAge = Tester.getMethod(father, "getAge");
                Method getGender = Tester.getMethod(father, "getGender");

                try {
                    System.out.println(getName.invoke(father));
                    System.out.println(getAge.invoke(father));
                    System.out.println(getGender.invoke(father));
                } catch (Exception e) {
                }
            } else if (code == 3) {
                Method setName = Tester.getMethod(father, "setName");
                Method setAge = Tester.getMethod(father, "setAge");
                Method setGender = Tester.getMethod(father, "setGender");

                String newName = "Test Name";
                int newAge = 100;
                char newGender = 'F';

                try {
                    setName.invoke(father, newName);
                    setAge.invoke(father, newAge);
                    setGender.invoke(father, newGender);
                } catch (Exception e) {
                }
            } else if (code == 4) {
                System.out.println(father);
                System.out.println(son1);
                System.out.println(son2);
            } else if (code == 5) {
                try {
                    System.out.println("isMinor test 1 = " + father.isMinor());
                    System.out.println("isMinor test 2 = " + son1.isMinor());
                    System.out.println("isMinor test 3 = " + son2.isMinor());
                } catch (Exception e) {
                }
            } else if (code == 6) {
                if (father instanceof Person) {
                    System.out.println("Person instance");
                }
            } else if (code == 7) {
                try {
                    Father newFather = new Father("Test", 25);
                    Field gender = Tester.getField(newFather, "gender");

                    if (gender != null) {
                        gender.setAccessible(true);
                        Object value = gender.get(newFather);
                        System.out.println("Gender = " + value);
                    }
                } catch (Exception e) {
                }
            } else if (code == 8) {
                Method introduceWithStyle = Tester.getMethod(father, "introduceWithStyle");
                try {
                    introduceWithStyle.invoke(father, 20);
                } catch (Exception e) {
                }
            } else if (code == 9) {
                if (son1 instanceof Father) {
                    System.out.println("Father instance");
                }
            } else if (code == 10) {
                try {
                    Son newSon = new Son(25);
                    Field name = Tester.getField(newSon, "name");

                    if (name != null) {
                        name.setAccessible(true);
                        Object value = name.get(newSon);
                        System.out.println("Name = " + value);
                    }
                } catch (Exception e) {
                }
            } else if (code == 11) {
                Method introduceWithStyle = Tester.getMethod(son1, "introduceWithStyle");
                try {
                    introduceWithStyle.invoke(son1, 20);
                } catch (Exception e) {
                }
            } else if (code == 12) {
                System.out.println("Son class is final = " + Modifier.isFinal(Son.class.getModifiers()));
            }
        }
    }
}