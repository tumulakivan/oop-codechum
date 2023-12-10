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

    public static void test(Number number) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field value = Tester.getField(number, "value");

            if (value != null && Modifier.isPrivate(value.getModifiers())) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getValue = Tester.getMethod(number, "getValue");
            Method setValue = Tester.getMethod(number, "setValue");

            try {
                System.out.println(getValue.invoke(number));
                setValue.invoke(number, 1);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            Method multiply = Tester.getMethod(number, "multiply");
            Number newNumber = null;
            if (number instanceof WholeNumber) {
                newNumber = new WholeNumber(1);
            } else if (number instanceof DecimalNumber) {
                newNumber = new DecimalNumber(1, 4);
            }

            try {
                multiply.invoke(number, newNumber);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 4) {
            if (number instanceof DecimalNumber) {
                Method getDecimalPlaces = Tester.getMethod(number, "getDecimalPlaces");
                Method setDecimalPlaces = Tester.getMethod(number, "setDecimalPlaces");

                try {
                    System.out.println(getDecimalPlaces.invoke(number));
                    setDecimalPlaces.invoke(number, 1);
                    System.out.println("SUCCESS");
                } catch (Exception e) {
                    System.out.println("FAILED");
                }
            }
        } else if (code == 5) {
            try {
                Method multiply = Tester.getMethod(number, "multiply");
                Method getValue = Tester.getMethod(number, "getValue");

                int value = Integer.parseInt(getValue.invoke(number).toString());
                int expectedValue;
                int expectedDecimalPlaces;

                if (number instanceof WholeNumber) {
                    WholeNumber newWholeNumber = new WholeNumber(5);

                    Method getValue2 = Tester.getMethod(newWholeNumber, "getValue");
                    int value2 = Integer.parseInt(getValue2.invoke(newWholeNumber).toString());

                    expectedValue = value * value2;
                    multiply.invoke(number, newWholeNumber);

                    int newValue = Integer.parseInt(getValue.invoke(number).toString());

                    if (expectedValue == newValue) {
                        System.out.println("SUCCESS");
                        return;
                    }
                } else if (number instanceof DecimalNumber) {
                    Method getDecimalPlaces = Tester.getMethod(number, "getDecimalPlaces");
                    int decimalPlaces = Integer.parseInt(getDecimalPlaces.invoke(number).toString());

                    DecimalNumber newDecimalNumber = new DecimalNumber(2, 4);

                    Method getValue2 = Tester.getMethod(newDecimalNumber, "getValue");
                    Method getDecimalPlaces2 = Tester.getMethod(newDecimalNumber, "getDecimalPlaces");
                    int value2 = Integer.parseInt(getValue.invoke(newDecimalNumber).toString());
                    int decimalPlaces2 = Integer.parseInt(getDecimalPlaces2.invoke(newDecimalNumber).toString());

                    expectedValue = value * value2;
                    expectedDecimalPlaces = decimalPlaces + decimalPlaces2;
                    multiply.invoke(number, newDecimalNumber);

                    int newValue = Integer.parseInt(getValue.invoke(number).toString());
                    int newDecimalPlaces = Integer.parseInt(getDecimalPlaces.invoke(number).toString());

                    if (expectedValue == newValue && expectedDecimalPlaces == newDecimalPlaces) {
                        System.out.println("SUCCESS");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 6) {
            try {
                Method getValue = Tester.getMethod(number, "getValue");
                Method setValue = Tester.getMethod(number, "setValue");

                setValue.invoke(number, 25);

                String value = getValue.invoke(number).toString();
                String expected;

                if (number instanceof WholeNumber) {
                    expected = value;

                    if (expected.equals(number.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    }
                } else if (number instanceof DecimalNumber) {
                    Method getDecimalPlaces = Tester.getMethod(number, "getDecimalPlaces");
                    Method setDecimalPlaces = Tester.getMethod(number, "setDecimalPlaces");

                    setDecimalPlaces.invoke(number, 4);

                    int decimalPlaces = Integer.parseInt(getDecimalPlaces.invoke(number).toString());

                    expected = "0.0025";

                    if (expected.equals(number.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 7) {
            int testValue = 1;
            Number newNumber = new Number(testValue);

            Field value = Tester.getField(newNumber, "value");

            try {
                if (value != null) {
                    value.setAccessible(true);
                    Object valueObj = value.get(newNumber);

                    if (!(Integer.parseInt(valueObj.toString()) == testValue)) {
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
        } else if (code == 8) {
            WholeNumber newWholeNumber = new WholeNumber(25);
            DecimalNumber newDecimalNumber = new DecimalNumber(25, 4);

            if (newWholeNumber instanceof Number && newDecimalNumber instanceof Number) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        } else if (code == 9) {
            if (number instanceof DecimalNumber) {
                Field decimalPlaces = Tester.getField(number, "decimalPlaces");

                if (decimalPlaces != null && Modifier.isPrivate(decimalPlaces.getModifiers())) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILED");
                }
            }
        } else if (code == 10) {
            try {
                if (number instanceof DecimalNumber) {
                    Method getDecimalPlaces = Tester.getMethod(number, "getDecimalPlaces");
                    Field decimalPlaces = Tester.getField(number, "decimalPlaces");

                    if (decimalPlaces != null) {
                        decimalPlaces.setAccessible(true);
                        Object value = decimalPlaces.get(number);
                        if (!value.toString().equals(getDecimalPlaces.invoke(number).toString())) {
                            System.out.println("FAILED");
                            return;
                        }
                    } else {
                        System.out.println("FAILED");
                        return;
                    }
                    System.out.println("SUCCESS");
                    return;
                }

            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}