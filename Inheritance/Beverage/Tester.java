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

    public static void test(Beverage beverage) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field name = Tester.getField(beverage, "name");
            Field volume = Tester.getField(beverage, "volume");
            Field isChilled = Tester.getField(beverage, "isChilled");

            if ((name != null && Modifier.isPrivate(name.getModifiers())) &&
                    (volume != null && Modifier.isPrivate(volume.getModifiers())) &&
                    (isChilled != null && Modifier.isPrivate(isChilled.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getName = Tester.getMethod(beverage, "getName");
            Method getVolume = Tester.getMethod(beverage, "getVolume");
            Method getIsChilled = Tester.getMethod(beverage, "getIsChilled");

            try {
                System.out.println(getName.invoke(beverage));
                System.out.println(getVolume.invoke(beverage));
                System.out.println(getIsChilled.invoke(beverage));
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            Method isEmpty = Tester.getMethod(beverage, "isEmpty");
            Method getVolume = Tester.getMethod(beverage, "getVolume");
            try {
                boolean isEmptyTest = Integer.parseInt(getVolume.invoke(beverage).toString()) == 0;
                String stringIsEmpty = isEmpty.invoke(beverage).toString();
                if (isEmptyTest == Boolean.parseBoolean(stringIsEmpty)) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILED");
                }

            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 4) {
            try {
                Method getIsChilled = Tester.getMethod(beverage, "getIsChilled");
                Method getVolume = Tester.getMethod(beverage, "getVolume");

                boolean isChilled = Boolean.parseBoolean(getIsChilled.invoke(beverage).toString());
                String isChilledStr = isChilled ? "is still chilled" : "is not chilled anymore";
                int volume = Integer.parseInt(getVolume.invoke(beverage).toString());
                String expected;
                if (beverage instanceof Water) {
                    expected = "Water" + " (" + volume + "mL) " + isChilledStr;

                    if (expected.equals(beverage.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    }
                } else if (beverage instanceof Beer) {
                    Method getAlcoholicContent = Tester.getMethod(beverage, "getAlcoholicContent");
                    double alcoholicContent = Double.parseDouble(getAlcoholicContent.invoke(beverage).toString());
                    expected = "Beer" + " (" + volume + "mL) " + isChilledStr + " ("
                            + String.format("%.1f", alcoholicContent * 100) + "% alcoholic content)";
                    if (expected.equals(beverage.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    }
                }
                System.out.println("FAILED");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 5) {
            String testName = "Test";
            int testVolume = 100;
            boolean testIsChilled = true;
            Beverage newBeverage = new Beverage(testName, testVolume, testIsChilled);
            Field name = Tester.getField(newBeverage, "name");
            Field volume = Tester.getField(newBeverage, "volume");
            Field isChilled = Tester.getField(newBeverage, "isChilled");

            try {
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(newBeverage);
                    if (!value.toString().equals(testName)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (volume != null) {
                    volume.setAccessible(true);
                    Object value = volume.get(newBeverage);
                    if (!(Integer.parseInt(value.toString()) == testVolume)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (isChilled != null) {
                    isChilled.setAccessible(true);
                    Object value = isChilled.get(newBeverage);
                    if (!(Boolean.parseBoolean(value.toString()) == testIsChilled)) {
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
        } else if (code == 6) {
            Water newWater = new Water(100, true, "Regular");
            Beer newBeer = new Beer(100, true, 0.05);

            if (newWater instanceof Beverage && newBeer instanceof Beverage) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        } else if (code == 7) {
            if (Modifier.isFinal(Water.class.getModifiers()) && Modifier.isFinal(Beer.class.getModifiers())) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        } else if (code == 8) {
            try {
                if (beverage instanceof Water) {
                    Field type = Tester.getField(beverage, "type");
                    if ((type != null && Modifier.isPrivate(type.getModifiers()))) {
                        System.out.println("SUCCESS");
                    }
                } else if (beverage instanceof Beer) {
                    Field alcoholicContent = Tester.getField(beverage, "alcoholicContent");
                    if ((alcoholicContent != null && Modifier.isPrivate(alcoholicContent.getModifiers()))) {
                        System.out.println("SUCCESS");
                    }
                } else {
                    System.out.println("FAILED");
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }

        } else if (code == 9) {
            try {
                if (beverage instanceof Water) {
                    Method getType = Tester.getMethod(beverage, "getType");
                    Field type = Tester.getField(beverage, "type");

                    if (type != null) {
                        type.setAccessible(true);
                        Object value = type.get(beverage);
                        if (!value.toString().equals(getType.invoke(beverage).toString())) {
                            System.out.println("FAILED");
                            return;
                        }
                    } else {
                        System.out.println("FAILED");
                        return;
                    }
                    System.out.println("SUCCESS");
                    return;
                } else if (beverage instanceof Beer) {
                    Method getAlcoholicContent = Tester.getMethod(beverage, "getAlcoholicContent");
                    Field alcoholicContent = Tester.getField(beverage, "alcoholicContent");

                    if (alcoholicContent != null) {
                        alcoholicContent.setAccessible(true);
                        Object value = alcoholicContent.get(beverage);
                        if (!(Double.parseDouble(value.toString()) == Double
                                .parseDouble(getAlcoholicContent.invoke(beverage).toString()))) {
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
        } else if (code == 10) {
            try {
                Water water1 = new Water(100, true, "Distilled");
                Water water2 = new Water(100, false);

                Beer beer1 = new Beer(100, true, 0.02);
                Beer beer2 = new Beer(100, true, 0.05);
                Beer beer3 = new Beer(100, false, 0.1);

                Field type1 = Tester.getField(water1, "type");
                Field type2 = Tester.getField(water2, "type");

                Method method1 = Tester.getMethod(beer1, "getType");
                Method method2 = Tester.getMethod(beer2, "getType");
                Method method3 = Tester.getMethod(beer3, "getType");

                if (water1 != null) {
                    type1.setAccessible(true);
                    Object value = type1.get(water1);
                    if (!value.toString().equals("Distilled")) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (water2 != null) {
                    type2.setAccessible(true);
                    Object value = type2.get(water2);
                    if (!value.toString().equals("Regular")) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (beer1 != null) {
                    Object value = method1.invoke(beer1);
                    if (!value.toString().equals("Flavored")) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (beer2 != null) {
                    Object value = method2.invoke(beer2);
                    if (!value.toString().equals("Regular")) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (beer3 != null) {

                    Object value = method3.invoke(beer3);
                    if (!value.toString().equals("Strong")) {
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
        }
    }
}