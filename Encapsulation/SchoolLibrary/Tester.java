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

    public static void test(Book book) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();

        if (code == 1) {
            Field title = Tester.getField(book, "title");
            Field numberOfPages = Tester.getField(book, "numberOfPages");
            Field isFictional = Tester.getField(book, "isFictional");

            if ((title != null && Modifier.isPrivate(title.getModifiers())) &&
                    (numberOfPages != null && Modifier.isPrivate(numberOfPages.getModifiers())) &&
                    (isFictional != null && Modifier.isPrivate(isFictional.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getTitle = Tester.getMethod(book, "getTitle");
            Method getNumberOfPages = Tester.getMethod(book, "getNumberOfPages");
            Method getIsFictional = Tester.getMethod(book, "getIsFictional");

            try {
                getTitle.invoke(book);
                getNumberOfPages.invoke(book);
                getIsFictional.invoke(book);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            Method setTitle = Tester.getMethod(book, "setTitle");
            Method setNumberOfPages = Tester.getMethod(book, "setNumberOfPages");
            Method setIsFictional = Tester.getMethod(book, "setIsFictional");

            try {
                setTitle.invoke(book, "Test");
                setNumberOfPages.invoke(book, 5);
                setIsFictional.invoke(book, true);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 4) {
            String testTitle = "Test";
            int testNumberOfPages = 5;
            boolean testIsFictional = true;
            Book newbook = new Book();

            Method setTitle = Tester.getMethod(newbook, "setTitle");
            Method setNumberOfPages = Tester.getMethod(newbook, "setNumberOfPages");
            Method setIsFictional = Tester.getMethod(newbook, "setIsFictional");

            try {
                setTitle.invoke(newbook, testTitle);
                setNumberOfPages.invoke(newbook, testNumberOfPages);
                setIsFictional.invoke(newbook, testIsFictional);

                Field title = Tester.getField(newbook, "title");
                Field numberOfPages = Tester.getField(newbook, "numberOfPages");
                Field isFictional = Tester.getField(newbook, "isFictional");
                if (title != null) {
                    title.setAccessible(true);
                    Object value = title.get(newbook);
                    if (!(value.toString() == testTitle)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (numberOfPages != null) {
                    numberOfPages.setAccessible(true);
                    Object value = numberOfPages.get(newbook);
                    if (!(Integer.parseInt(value.toString()) == testNumberOfPages)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (isFictional != null) {
                    isFictional.setAccessible(true);
                    Object value = isFictional.get(newbook);
                    if (!(Boolean.parseBoolean(value.toString()) == testIsFictional)) {
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
            String testTitle = "Test";
            Book newbook = new Book();

            Method getTitle = Tester.getMethod(newbook, "getTitle");
            Method setTitle = Tester.getMethod(newbook, "setTitle");

            try {
                setTitle.invoke(newbook, testTitle);
                String title = getTitle.invoke(newbook).toString();

                if (!(title.equals(testTitle))) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 6) {
            int testNumberOfPages = 5;
            Book newbook = new Book();

            Method getNumberOfPages = Tester.getMethod(newbook, "getNumberOfPages");
            Method setNumberOfPages = Tester.getMethod(newbook, "setNumberOfPages");

            try {
                setNumberOfPages.invoke(newbook, testNumberOfPages);
                int numberOfPages = Integer.parseInt(getNumberOfPages.invoke(newbook).toString());

                if (!(numberOfPages == testNumberOfPages)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 7) {
            boolean testIsFictional = true;
            Book newbook = new Book();

            Method getIsFictional = Tester.getMethod(newbook, "getIsFictional");
            Method setIsFictional = Tester.getMethod(newbook, "setIsFictional");

            try {
                setIsFictional.invoke(newbook, testIsFictional);
                boolean isFictional = Boolean.parseBoolean(getIsFictional.invoke(newbook).toString());

                if (!(isFictional == testIsFictional)) {
                    System.out.println("FAILED");
                    return;
                }

                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 8) {
            String testTitle = "Test";
            int testNumberOfPages = 5;
            boolean testIsFictional = true;
            Book newbook = new Book();

            Method getTitle = Tester.getMethod(newbook, "getTitle");
            Method getNumberOfPages = Tester.getMethod(newbook, "getNumberOfPages");
            Method getIsFictional = Tester.getMethod(newbook, "getIsFictional");

            Method setTitle = Tester.getMethod(newbook, "setTitle");
            Method setNumberOfPages = Tester.getMethod(newbook, "setNumberOfPages");
            Method setIsFictional = Tester.getMethod(newbook, "setIsFictional");

            try {
                setTitle.invoke(newbook, testTitle);
                setNumberOfPages.invoke(newbook, testNumberOfPages);
                setIsFictional.invoke(newbook, testIsFictional);

                String title = getTitle.invoke(newbook).toString();
                int numberOfPages = Integer.parseInt(getNumberOfPages.invoke(newbook).toString());
                boolean isFictional = Boolean.parseBoolean(getIsFictional.invoke(newbook).toString());

                if (!(title.equals(testTitle))) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(numberOfPages == testNumberOfPages)) {
                    System.out.println("FAILED");
                    return;
                }

                if (!(isFictional == testIsFictional)) {
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