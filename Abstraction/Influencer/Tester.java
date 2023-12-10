package Influencer;

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

    public static void test(Influencer influencer) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            if(Modifier.isAbstract(Influencer.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            Field name = Tester.getField(influencer, "name");
            Field platform = Tester.getField(influencer, "platform");

            if ((name != null && Modifier.isPrivate(name.getModifiers())) &&
                (platform != null && Modifier.isPrivate(platform.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            try {
                Method doLiveStream = Influencer.class.getDeclaredMethod("doLiveStream");

                if (doLiveStream != null) {
                    if(Modifier.isAbstract(doLiveStream.getModifiers())) {
                        System.out.println("SUCCESS");
                    }else {
                        System.out.println("FAILED");
                    }
                }else {
                     System.out.println("FAILED");
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 4) {
            if (influencer instanceof Influencer) {
                System.out.println("SUCCESS");
            }
            else {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            String testName = "Test";
            String testPlatform = "Tiktok";

            TiktokInfluencer newInfluencer = new TiktokInfluencer(testName);

            Field name = Tester.getField(newInfluencer, "name");
            Field platform = Tester.getField(newInfluencer, "platform");

            try {
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(newInfluencer);
                    if (!(value.toString() == testName)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (platform != null) {
                    platform.setAccessible(true);
                    Object value = platform.get(newInfluencer);
                    if (!(value.toString() == testPlatform)) {
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
        else if (code == 6) {
            String testName = "Test";
            String testPlatform = "Facebook";

            FacebookInfluencer newInfluencer = new FacebookInfluencer(testName);

            Field name = Tester.getField(newInfluencer, "name");
            Field platform = Tester.getField(newInfluencer, "platform");

            try {
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(newInfluencer);
                    if (!(value.toString() == testName)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (platform != null) {
                    platform.setAccessible(true);
                    Object value = platform.get(newInfluencer);
                    if (!(value.toString() == testPlatform)) {
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
            Method doLiveStream = Tester.getMethod(influencer, "doLiveStream");
            try {
                if (influencer instanceof Influencer) {
                    doLiveStream.invoke(influencer);
                    System.out.println("SUCCESS");
                }else {
                    System.out.println("FAILED");
                }
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            String testName = "Test";
            FacebookInfluencer newInfluencer = new FacebookInfluencer(testName);
            String expected;

            if (newInfluencer instanceof Influencer) {
                expected = "I'm " + testName + " an influencer at Facebook";

                if (expected.equals(newInfluencer.toString())) {
                    System.out.println("SUCCESS");
                    return;
                }else {
                    System.out.println("FAILED");
                }
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 9) {
            String testName = "Test";
            TiktokInfluencer newInfluencer = new TiktokInfluencer(testName);
            String expected;

            if (newInfluencer instanceof Influencer) {
                expected = "I'm " + testName + " an influencer at Tiktok";

                if (expected.equals(newInfluencer.toString())) {
                    System.out.println("SUCCESS");
                    return;
                }else {
                    System.out.println("FAILED");
                }
            }else {
                System.out.println("FAILED");
            }
        }
    }
}