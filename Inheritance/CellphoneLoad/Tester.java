import java.util.*;
import java.lang.reflect.*;

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
        for(Field f : fields){
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

    public static void test(CellphoneLoad cellphoneLoad, Phone phone ) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field hasUnlimitedCalls = Tester.getField(cellphoneLoad, "hasUnlimitedCalls");
            Field hasUnlimitedTexts = Tester.getField(cellphoneLoad, "hasUnlimitedTexts");
            Field internetMB = Tester.getField(cellphoneLoad, "internetMB");

            if(
                (hasUnlimitedCalls != null && Modifier.isPrivate(hasUnlimitedCalls.getModifiers())) &&
                (hasUnlimitedTexts != null && Modifier.isPrivate(hasUnlimitedTexts.getModifiers())) &&
                (internetMB != null && Modifier.isPrivate(internetMB.getModifiers()))
            ) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            Method getHasUnlimitedCalls = Tester.getMethod(cellphoneLoad, "getHasUnlimitedCalls");
            Method getHasUnlimitedTexts = Tester.getMethod(cellphoneLoad, "getHasUnlimitedTexts");
            Method getInternetMB = Tester.getMethod(cellphoneLoad, "getInternetMB");

            try {
                System.out.println(getHasUnlimitedCalls.invoke(cellphoneLoad));
                System.out.println(getHasUnlimitedTexts.invoke(cellphoneLoad));
                System.out.println(getInternetMB.invoke(cellphoneLoad));
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            boolean testHasUnlimitedCalls = true;
            boolean testHasUnlimitedTexts = false;
            int testInternetMB = 100;
            CellphoneLoad newCellphoneLoad = new CellphoneLoad(testHasUnlimitedCalls, testHasUnlimitedTexts, testInternetMB);

            Field hasUnlimitedCalls = Tester.getField(newCellphoneLoad, "hasUnlimitedCalls");
            Field hasUnlimitedTexts = Tester.getField(newCellphoneLoad, "hasUnlimitedTexts");
            Field internetMB = Tester.getField(newCellphoneLoad, "internetMB");

            try {
                if(hasUnlimitedCalls != null) {
                    hasUnlimitedCalls.setAccessible(true);
                    Object value = hasUnlimitedCalls.get(newCellphoneLoad);
                    if (!(Boolean.parseBoolean(value.toString()) == testHasUnlimitedCalls)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if(hasUnlimitedTexts != null) {
                    hasUnlimitedTexts.setAccessible(true);
                    Object value = hasUnlimitedTexts.get(newCellphoneLoad);
                    if (!(Boolean.parseBoolean(value.toString()) == testHasUnlimitedTexts)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (internetMB != null) {
                    internetMB.setAccessible(true);
                    Object value = internetMB.get(newCellphoneLoad);
                    if (!(Integer.parseInt(value.toString()) == testInternetMB)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 4) {
            Method getHasUnlimitedCalls = Tester.getMethod(cellphoneLoad, "getHasUnlimitedCalls");
            Method getHasUnlimitedTexts = Tester.getMethod(cellphoneLoad, "getHasUnlimitedTexts");
            Method getInternetMB = Tester.getMethod(cellphoneLoad, "getInternetMB");

            boolean testHasUnlimitedCalls;
            boolean testHasUnlimitedTexts;
            int testInternetMB;

            try {
                boolean hasUnlimitedCalls = Boolean.parseBoolean(getHasUnlimitedCalls.invoke(cellphoneLoad).toString());
                boolean hasUnlimitedTexts = Boolean.parseBoolean(getHasUnlimitedTexts.invoke(cellphoneLoad).toString());
                int internetMB = Integer.parseInt(getInternetMB.invoke(cellphoneLoad).toString());

                if (cellphoneLoad instanceof CITLoad) {
                    testHasUnlimitedCalls = true;
                    testHasUnlimitedTexts = true;
                    testInternetMB = 1000;

                    if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                        hasUnlimitedTexts == testHasUnlimitedTexts &&
                        internetMB == testInternetMB) {
                            System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAILED");
                    }

                }
                else if (cellphoneLoad instanceof CTLoad) {
                    testHasUnlimitedCalls = true;
                    testHasUnlimitedTexts = true;
                    testInternetMB = 0;

                    if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                        hasUnlimitedTexts == testHasUnlimitedTexts &&
                        internetMB == testInternetMB) {
                            System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAILED");
                    }

                }
                else if (cellphoneLoad instanceof ILoad) {
                    testHasUnlimitedCalls = false;
                    testHasUnlimitedTexts = false;
                    testInternetMB = 2000;

                    if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                        hasUnlimitedTexts == testHasUnlimitedTexts &&
                        internetMB == testInternetMB) {
                            System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAILED");
                    }
                }
                else {
                    System.out.println("FAILED");
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            Field hasUnlimitedCalls = Tester.getField(phone, "hasUnlimitedCalls");
            Field hasUnlimitedTexts = Tester.getField(phone, "hasUnlimitedTexts");
            Field internetMB = Tester.getField(phone, "internetMB");

            if(
                (hasUnlimitedCalls != null && Modifier.isPrivate(hasUnlimitedCalls.getModifiers())) &&
                (hasUnlimitedTexts != null && Modifier.isPrivate(hasUnlimitedTexts.getModifiers())) &&
                (internetMB != null && Modifier.isPrivate(internetMB.getModifiers()))
            ) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 6) {
            Method load = Tester.getMethod(phone, "load");

            try {
                load.invoke(phone, cellphoneLoad);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 7) {
            boolean testHasUnlimitedCalls = false;
            boolean testHasUnlimitedTexts = false;
            int testInternetMB = 0;

            Phone newPhone = new Phone();

            Field hasUnlimitedCallsField = Tester.getField(newPhone, "hasUnlimitedCalls");
            Field hasUnlimitedTextsField = Tester.getField(newPhone, "hasUnlimitedTexts");
            Field internetMBField = Tester.getField(newPhone, "internetMB");

            try {
                if (hasUnlimitedCallsField != null && hasUnlimitedTextsField !=null && internetMBField != null ) {
                    hasUnlimitedCallsField.setAccessible(true);
                    hasUnlimitedTextsField.setAccessible(true);
                    internetMBField.setAccessible(true);

                    Object value1 = hasUnlimitedCallsField.get(newPhone);
                    Object value2 = hasUnlimitedTextsField.get(newPhone);
                    Object value3 = internetMBField.get(newPhone);

                    boolean hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                    boolean hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                    int internetMB = Integer.parseInt(value3.toString());

                    if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                        hasUnlimitedTexts == testHasUnlimitedTexts &&
                        internetMB == testInternetMB) {
                        System.out.println("SUCCESS");
                    } else {
                        System.out.println("FAILED");
                    }
                } else {
                    System.out.println("FAILED");
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            Method load = Tester.getMethod(phone, "load");
            boolean testHasUnlimitedCalls;
            boolean testHasUnlimitedTexts;
            int testInternetMB;

            try {
                load.invoke(phone, cellphoneLoad);

                Field hasUnlimitedCallsField = Tester.getField(phone, "hasUnlimitedCalls");
                Field hasUnlimitedTextsField = Tester.getField(phone, "hasUnlimitedTexts");
                Field internetMBField = Tester.getField(phone, "internetMB");

                if (hasUnlimitedCallsField != null && hasUnlimitedTextsField !=null && internetMBField != null ) {
                    hasUnlimitedCallsField.setAccessible(true);
                    hasUnlimitedTextsField.setAccessible(true);
                    internetMBField.setAccessible(true);

                    Object value1 = hasUnlimitedCallsField.get(phone);
                    Object value2 = hasUnlimitedTextsField.get(phone);
                    Object value3 = internetMBField.get(phone);

                    boolean hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                    boolean hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                    int internetMB = Integer.parseInt(value3.toString());

                    if (cellphoneLoad instanceof CITLoad) {
                        testHasUnlimitedCalls = true;
                        testHasUnlimitedTexts = true;
                        testInternetMB = 1000;

                        if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                            hasUnlimitedTexts == testHasUnlimitedTexts &&
                            internetMB == testInternetMB) {
                            System.out.println("SUCCESS 1");

                            ILoad newILoad = new ILoad();
                            load.invoke(phone, newILoad);

                            hasUnlimitedCallsField = Tester.getField(phone, "hasUnlimitedCalls");
                            hasUnlimitedTextsField = Tester.getField(phone, "hasUnlimitedTexts");
                            internetMBField = Tester.getField(phone, "internetMB");

                            testHasUnlimitedCalls = false;
                            testHasUnlimitedTexts = false;
                            testInternetMB = 3000;

                            hasUnlimitedCallsField.setAccessible(true);
                            hasUnlimitedTextsField.setAccessible(true);
                            internetMBField.setAccessible(true);

                            value1 = hasUnlimitedCallsField.get(phone);
                            value2 = hasUnlimitedTextsField.get(phone);
                            value3 = internetMBField.get(phone);

                            hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                            hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                            internetMB = Integer.parseInt(value3.toString());

                            if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                                hasUnlimitedTexts == testHasUnlimitedTexts &&
                                internetMB == testInternetMB) {
                                    System.out.println("SUCCESS 2");
                            } else {
                                System.out.println("FAILED");
                            }
                        } else {
                            System.out.println("FAILED");
                        }
                    }
                    else if (cellphoneLoad instanceof CTLoad) {
                        testHasUnlimitedCalls = true;
                        testHasUnlimitedTexts = true;
                        testInternetMB = 0;

                        if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                            hasUnlimitedTexts == testHasUnlimitedTexts &&
                            internetMB == testInternetMB) {
                            System.out.println("SUCCESS 1");

                            ILoad newILoad = new ILoad();
                            load.invoke(phone, newILoad);

                            hasUnlimitedCallsField = Tester.getField(phone, "hasUnlimitedCalls");
                            hasUnlimitedTextsField = Tester.getField(phone, "hasUnlimitedTexts");
                            internetMBField = Tester.getField(phone, "internetMB");

                            testHasUnlimitedCalls = false;
                            testHasUnlimitedTexts = false;
                            testInternetMB = 2000;

                            hasUnlimitedCallsField.setAccessible(true);
                            hasUnlimitedTextsField.setAccessible(true);
                            internetMBField.setAccessible(true);

                            value1 = hasUnlimitedCallsField.get(phone);
                            value2 = hasUnlimitedTextsField.get(phone);
                            value3 = internetMBField.get(phone);

                            hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                            hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                            internetMB = Integer.parseInt(value3.toString());

                            if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                                hasUnlimitedTexts == testHasUnlimitedTexts &&
                                internetMB == testInternetMB) {
                                        System.out.println("SUCCESS 2");
                            } else {
                                System.out.println("FAILED");
                            }

                        } else {
                            System.out.println("FAILED");
                        }
                    }
                    else if (cellphoneLoad instanceof ILoad) {
                        testHasUnlimitedCalls = false;
                        testHasUnlimitedTexts = false;
                        testInternetMB = 2000;

                        if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                            hasUnlimitedTexts == testHasUnlimitedTexts &&
                            internetMB == testInternetMB) {
                            System.out.println("SUCCESS 1");

                            CITLoad newCITLoad = new CITLoad();
                            load.invoke(phone, newCITLoad);

                            hasUnlimitedCallsField = Tester.getField(phone, "hasUnlimitedCalls");
                            hasUnlimitedTextsField = Tester.getField(phone, "hasUnlimitedTexts");
                            internetMBField = Tester.getField(phone, "internetMB");

                            testHasUnlimitedCalls = true;
                            testHasUnlimitedTexts = true;
                            testInternetMB = 3000;

                            hasUnlimitedCallsField.setAccessible(true);
                            hasUnlimitedTextsField.setAccessible(true);
                            internetMBField.setAccessible(true);

                            value1 = hasUnlimitedCallsField.get(phone);
                            value2 = hasUnlimitedTextsField.get(phone);
                            value3 = internetMBField.get(phone);

                            hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                            hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                            internetMB = Integer.parseInt(value3.toString());

                            if (hasUnlimitedCalls == testHasUnlimitedCalls &&
                                hasUnlimitedTexts == testHasUnlimitedTexts &&
                                internetMB == testInternetMB) {
                                        System.out.println("SUCCESS 2");
                            } else {
                                System.out.println("FAILED");
                            }
                        } else {
                            System.out.println("FAILED");
                        }
                    }
                    else {
                        System.out.println("FAILED");
                    }
                }
                else {
                    System.out.println("FAILED");
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 9) {
            Field hasUnlimitedCallsField = Tester.getField(phone, "hasUnlimitedCalls");
            Field hasUnlimitedTextsField = Tester.getField(phone, "hasUnlimitedTexts");
            Field internetMBField = Tester.getField(phone, "internetMB");

            try {
                if (hasUnlimitedCallsField != null && hasUnlimitedTextsField !=null && internetMBField != null ) {
                    hasUnlimitedCallsField.setAccessible(true);
                    hasUnlimitedTextsField.setAccessible(true);
                    internetMBField.setAccessible(true);

                    Object value1 = hasUnlimitedCallsField.get(phone);
                    Object value2 = hasUnlimitedTextsField.get(phone);
                    Object value3 = internetMBField.get(phone);

                    boolean hasUnlimitedCalls =  Boolean.parseBoolean(value1.toString());
                    boolean hasUnlimitedTexts =  Boolean.parseBoolean(value2.toString());
                    int internetMB = Integer.parseInt(value3.toString());

                    String expected = "Has unlimited calls = " + hasUnlimitedCalls + ", Has unlimited texts = " + hasUnlimitedTexts + ", internet MB = " + internetMB;

                    if (expected.equals(phone.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    }else {
                        System.out.println("FAILED");
                    }
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}