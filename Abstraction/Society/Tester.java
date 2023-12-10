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
    
    public static void test(FamilyMember father, FamilyMember mother, FamilyMember son) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field typeFather = Tester.getField(father, "type");
            Field typeMother = Tester.getField(mother, "type");
            Field typeSon = Tester.getField(son, "type");

            if ((typeFather != null && Modifier.isPrivate(typeFather.getModifiers())) && 
                (typeMother != null && Modifier.isPrivate(typeMother.getModifiers())) &&
                (typeSon != null && Modifier.isPrivate(typeSon.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            if(Modifier.isAbstract(FamilyMember.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            try {
                Method greet = FamilyMember.class.getDeclaredMethod("greet");
                if (greet != null) {
                    if(Modifier.isAbstract(greet.getModifiers())) {
                        System.out.println("SUCCESS");
                    }else {
                        System.out.println("FAILED");
                    }
                }
            } 
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 4) {
            String testType = "Father";
            Father newFather = new Father();
            Field type = Tester.getField(newFather, "type");

            try {
                if(type != null) {
                    type.setAccessible(true);
                    Object value = type.get(newFather);
                    if (!value.toString().equals(testType)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e ) {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            String testType = "Mother";
            Mother newMother = new Mother();
            Field type = Tester.getField(newMother, "type");

            try {
                if(type != null) {
                    type.setAccessible(true);
                    Object value = type.get(newMother);
                    if (!value.toString().equals(testType)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e ) {
                System.out.println("FAILED");
            }
        }
        else if (code == 6) {
            String testType = "Son";
            Son newSon = new Son();
            Field type = Tester.getField(newSon, "type");

            try {
                if(type != null) {
                    type.setAccessible(true);
                    Object value = type.get(newSon);
                    if (!value.toString().equals(testType)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e ) {
                System.out.println("FAILED");
            }
        }
        else if (code == 7) {
            Method greet = Tester.getMethod(father, "greet");

            try {
                greet.invoke(father);
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            Method greet = Tester.getMethod(mother, "greet");

            try {
                greet.invoke(mother);
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 9) {
            Method greet = Tester.getMethod(son, "greet");

            try {
                greet.invoke(son);
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 10) {
            Father newFather = new Father();

            if(newFather instanceof FamilyMember) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        }
        else if (code == 11) {
            Mother newMother = new Mother();

            if(newMother instanceof FamilyMember) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        }
        else if (code == 12) {
            Son newSon = new Son();

            if(newSon instanceof FamilyMember) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        }
        else if (code == 13) {
            String expected;

            if (father instanceof FamilyMember) {
                expected = "Family Member [Father]";

                if (expected.equals(father.toString())) {
                    System.out.println("SUCCESS");
                    return;
                }else {
                    System.out.println("FAILED");
                }
            }
        }
        else if (code == 14) {
            String expected;

            if (mother instanceof FamilyMember) {
                expected = "Family Member [Mother]";

                if (expected.equals(mother.toString())) {
                    System.out.println("SUCCESS");
                    return;
                }else {
                    System.out.println("FAILED");
                }
            }
        }
        else if (code == 15) {
            String expected;

            if (son instanceof FamilyMember) {
                expected = "Family Member [Son]";

                if (expected.equals(son.toString())) {
                    System.out.println("SUCCESS");
                    return;
                }else {
                    System.out.println("FAILED");
                }
            }
        }
    }
}