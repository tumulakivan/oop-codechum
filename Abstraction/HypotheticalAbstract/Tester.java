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
    
    public static void test(HypotheticalAbstract1 hypothethical) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            if(Modifier.isAbstract(HypotheticalAbstract1.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            if(Modifier.isAbstract(HypotheticalAbstract2.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            if(Modifier.isAbstract(HypotheticalAbstract3.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 4) {
            Field a = Tester.getField(hypothethical, "a");
            Field b = Tester.getField(hypothethical, "b");

            if ((a != null && Modifier.isProtected(a.getModifiers())) && 
                (b != null && Modifier.isProtected(b.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            try {
                Method getValue1 = HypotheticalAbstract1.class.getDeclaredMethod("getValue1");
                Method getValue2 = HypotheticalAbstract1.class.getDeclaredMethod("getValue2");

                if (getValue1 != null) {
                    if(Modifier.isAbstract(getValue1.getModifiers())) {
                        System.out.println("SUCCESS 1");
                    }else {
                        System.out.println("FAILED 1");
                    }
                }else {
                     System.out.println("FAILED 1");
                }

                if (getValue2 != null) {
                    if(Modifier.isAbstract(getValue2.getModifiers())) {
                        System.out.println("SUCCESS 2");
                    }else {
                        System.out.println("FAILED 2");
                    }
                }else {
                     System.out.println("FAILED 2");
                }
            } 
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if(code == 6) {
            int testA = 2;
            int testB = 5;

            HypotheticalNonAbstract newHypotheticalNonAbstract = new HypotheticalNonAbstract(testA, testB);

            Field a = Tester.getField(newHypotheticalNonAbstract, "a");
            Field b = Tester.getField(newHypotheticalNonAbstract, "b");

            try {
                if (a != null) {
                    a.setAccessible(true);
                    Object value = a.get(newHypotheticalNonAbstract);
                    if (!(Integer.parseInt(value.toString()) == testA)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (b != null) {
                    b.setAccessible(true);
                    Object value = b.get(newHypotheticalNonAbstract);
                    if (!(Integer.parseInt(value.toString()) == testB)) {
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
        else if (code == 7) {
            Method getValue1 = Tester.getMethod(hypothethical, "getValue1");
            Method getValue2 = Tester.getMethod(hypothethical, "getValue2");
    
            try {
                getValue1.invoke(hypothethical);
                getValue2.invoke(hypothethical);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            if(hypothethical instanceof HypotheticalAbstract1 && hypothethical instanceof HypotheticalAbstract2 && hypothethical instanceof HypotheticalAbstract3) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 9) {
            Method getValue1 = Tester.getMethod(hypothethical, "getValue1");

            try {
                Field filedA = Tester.getField(hypothethical, "a");
                Field filedB = Tester.getField(hypothethical, "b");

                if (filedA != null && filedB != null) {
                    filedA.setAccessible(true);
                    filedB.setAccessible(true);
                    Object value1 = filedA.get(hypothethical);
                    Object value2 = filedB.get(hypothethical);
                    int a = Integer.parseInt(value1.toString());
                    int b = Integer.parseInt(value2.toString());

                    int expected = a + b;
                    int result = Integer.parseInt(getValue1.invoke(hypothethical).toString());
                    
                    if(expected == result) {
                        System.out.println("SUCCESS");
                    }else {
                        System.out.println("FAILED");
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 10) {
            Method getValue2 = Tester.getMethod(hypothethical, "getValue2");

            try {
                Field filedA = Tester.getField(hypothethical, "a");
                Field filedB = Tester.getField(hypothethical, "b");

                if (filedA != null && filedB != null) {
                    filedA.setAccessible(true);
                    filedB.setAccessible(true);
                    Object value1 = filedA.get(hypothethical);
                    Object value2 = filedB.get(hypothethical);
                    int a = Integer.parseInt(value1.toString());
                    int b = Integer.parseInt(value2.toString());

                    int expected = a * b;
                    int result = Integer.parseInt(getValue2.invoke(hypothethical).toString());
                    
                    if(expected == result) {
                        System.out.println("SUCCESS");
                    }else {
                        System.out.println("FAILED");
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}