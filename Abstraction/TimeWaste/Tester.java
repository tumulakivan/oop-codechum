package TimeWaste;

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

    private static Method[] getMethods(Object obj, String methodName) {
        Method[] methods = obj.getClass().getMethods();
        Method[] methodsToReturn = new Method[methods.length];
        int i = 0;
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                methodsToReturn[i] = m;
                i++;
            }
        }
        return methodsToReturn;
    }
    
    public static void test() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Item item = new Item("test", "test");

            Field title = Tester.getField(item, "title");
            Field description = Tester.getField(item, "description");

            if ((title != null && Modifier.isPrivate(title.getModifiers())) && 
                (description != null && Modifier.isPrivate(description.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            Item item = new Item("test", "test");
            Method getTitle = Tester.getMethod(item, "getTitle");
            Method getDescription = Tester.getMethod(item, "getDescription");
    
            try {
                getTitle.invoke(item);
                getDescription.invoke(item);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            String testTitle = "Test";
            String testDescription = "Test";
            Item item = new Item(testTitle, testDescription);

            Field title = Tester.getField(item, "title");
            Field description = Tester.getField(item, "description");

            try {
                if (title != null) {
                    title.setAccessible(true);
                    Object value = title.get(item);
                    if (!(value.toString() == testTitle)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (description != null) {
                    description.setAccessible(true);
                    Object value = description.get(item);
                    if (!(value.toString() == testDescription)) {
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
            String testTitle = "Test";
            String testDescription = "Test";
            Item item = new Item(testTitle, testDescription);

            Method getTitle = Tester.getMethod(item, "getTitle");
            Method getDescription = Tester.getMethod(item, "getDescription");

            try {
                if (!(getTitle.invoke(item).toString() == testTitle)) {
                    System.out.println("FAILED");
                    return;
                }
            
                if (!(getDescription.invoke(item).toString() == testDescription)) {
                    System.out.println("FAILED");
                    return;
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            if(Modifier.isAbstract(SocialMedia.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 6) {
            try {
                Method getFeed = SocialMedia.class.getDeclaredMethod("getFeed", int.class);
                Method getFeed2 = SocialMedia.class.getDeclaredMethod("getFeed");

                if (getFeed != null && getFeed2 != null) {
                    if(Modifier.isAbstract(getFeed.getModifiers()) && Modifier.isAbstract(getFeed2.getModifiers())) {
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
        else if (code == 7) {
            Reddit reddit = new Reddit();
            Facebook facebook = new Facebook();

            if (reddit instanceof SocialMedia && facebook instanceof SocialMedia) {
                System.out.println("SUCCESS");
            }
            else {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            String testName = "FaCeBoOk";
            int testYearCreated = 2004;
            Facebook facebook = new Facebook();
            Field name = Tester.getField(facebook, "name");
            Field yearCreated = Tester.getField(facebook, "yearCreated");

            try {
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(facebook);
                    if (!(value.toString().equals(testName))) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (yearCreated != null) {
                    yearCreated.setAccessible(true);
                    Object value = yearCreated.get(facebook);
                    if (!(Integer.parseInt(value.toString()) == testYearCreated)) {
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
        else if (code == 9) {
            String testName = "Reddit";
            int testYearCreated = 2005;
            Reddit reddit = new Reddit();
            Field name = Tester.getField(reddit, "name");
            Field yearCreated = Tester.getField(reddit, "yearCreated");

            try {
                if (name != null) {
                    name.setAccessible(true);
                    Object value = name.get(reddit);
                    if (!(value.toString().equals(testName))) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (yearCreated != null) {
                    yearCreated.setAccessible(true);
                    Object value = yearCreated.get(reddit);
                    if (!(Integer.parseInt(value.toString()) == testYearCreated)) {
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
        else if (code == 10) {
            Facebook facebook = new Facebook();
            Method[] getFeed = Tester.getMethods(facebook, "getFeed");
            try {
                if (facebook instanceof SocialMedia) {
                    getFeed[0].invoke(facebook,10);
                    getFeed[1].invoke(facebook);
                    System.out.println("SUCCESS");
                }else {
                    System.out.println("FAILED");
                }
            }
            catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 11) {
            Reddit reddit = new Reddit();
            Method[] getFeed = Tester.getMethods(reddit, "getFeed");
            try {
                if (reddit instanceof SocialMedia) {
                    getFeed[0].invoke(reddit,10);
                    getFeed[1].invoke(reddit);
                    System.out.println("SUCCESS");
                }else {
                    System.out.println("FAILED");
                }
            }
            catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 12) {
            Facebook facebook = new Facebook();
            String expected;

            if (facebook instanceof SocialMedia) {
                expected = "FaCeBoOk" + " created last " + 2004;
                if (expected.equals(facebook.toString())) {
                    System.out.println(facebook);
                    System.out.println("SUCCESS");
                }else {
                    System.out.println("FAILED");
                }
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 13) {
            Reddit reddit = new Reddit();
            String expected;

            if (reddit instanceof SocialMedia) {
                expected = "Reddit" + " created last " + 2005;
                if (expected.equals(reddit.toString())) {
                    System.out.println(reddit);
                    System.out.println("SUCCESS");
                }else {
                    System.out.println("FAILED");
                }
            }else {
                System.out.println("FAILED");
            } 
        }
        else if (code == 14) {
            Facebook facebook = new Facebook();
            Field nameFB = Tester.getField(facebook, "NAME");
            Field yearCreatedFB = Tester.getField(facebook, "YEAR_CREATED");

            Reddit reddit = new Reddit();
            Field nameReddit = Tester.getField(reddit, "NAME");
            Field yearCreatedReddit = Tester.getField(reddit, "YEAR_CREATED");
            
            if (
                (nameFB != null && Modifier.isPrivate(nameFB.getModifiers())) && 
                (yearCreatedFB != null && Modifier.isPrivate(yearCreatedFB.getModifiers())) &&
                (nameFB != null && Modifier.isStatic(nameFB.getModifiers())) && 
                (yearCreatedFB != null && Modifier.isStatic(yearCreatedFB.getModifiers())) &&
                (nameFB != null && Modifier.isFinal(nameFB.getModifiers())) && 
                (yearCreatedFB != null && Modifier.isFinal(yearCreatedFB.getModifiers())) &&
                (nameReddit != null && Modifier.isPrivate(nameReddit.getModifiers())) && 
                (yearCreatedReddit != null && Modifier.isPrivate(yearCreatedReddit.getModifiers())) &&
                (nameReddit != null && Modifier.isStatic(nameReddit.getModifiers())) && 
                (yearCreatedReddit != null && Modifier.isStatic(yearCreatedReddit.getModifiers())) &&
                (nameReddit != null && Modifier.isFinal(nameReddit.getModifiers())) && 
                (yearCreatedReddit != null && Modifier.isFinal(yearCreatedReddit.getModifiers()))
            ) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 15) {
            Facebook facebook = new Facebook();
            Method[] getFeed = Tester.getMethods(facebook, "getFeed");

            try {
                Item[] items = (Item[]) getFeed[1].invoke(facebook);
                Method getTitle;
                Method getDescription;

                for (Item item : items) {
                    getTitle = Tester.getMethod(item, "getTitle");
                    getDescription = Tester.getMethod(item, "getDescription");

                    System.out.println(getTitle.invoke(item).toString());
                    System.out.println(getDescription.invoke(item).toString());
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }  
        else if (code == 16) {
            Reddit reddit = new Reddit();
            Method[] getFeed = Tester.getMethods(reddit, "getFeed");

            try {
                Item[] items = (Item[]) getFeed[1].invoke(reddit);
                Method getTitle;
                Method getDescription;

                for (Item item : items) {
                    getTitle = Tester.getMethod(item, "getTitle");
                    getDescription = Tester.getMethod(item, "getDescription");

                    System.out.println(getTitle.invoke(item).toString());
                    System.out.println(getDescription.invoke(item).toString());
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}