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

    public static void test(Character character, Swordsman swordsman) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field health = Tester.getField(character, "health");
            Field damage = Tester.getField(character, "damage");
            Field shield = Tester.getField(character, "shield");

            if(
                (health != null && Modifier.isPrivate(health.getModifiers())) &&
                (damage != null && Modifier.isPrivate(damage.getModifiers())) &&
                (shield != null && Modifier.isPrivate(shield.getModifiers()))
            ) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 2) {
            Method getHealth = Tester.getMethod(character, "getHealth");
            Method getDamage = Tester.getMethod(character, "getDamage");
            Method getShield = Tester.getMethod(character, "getShield");
            Method setHealth = Tester.getMethod(character, "setHealth");

            try {
                System.out.println(getHealth.invoke(character));
                System.out.println(getDamage.invoke(character));
                System.out.println(getShield.invoke(character));
                setHealth.invoke(character, 100);
                System.out.println("SUCCESS 1");

                int health = Integer.parseInt(getHealth.invoke(character).toString());
                if(health == 100) {
                    System.out.println("SUCCESS 2");
                } else {
                    System.out.println("FAILED");
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 3 ) {
            Method receiveDamage = Tester.getMethod(character, "receiveDamage");

            try {
                receiveDamage.invoke(character, 100);
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 4) {
            int testHealth = 100;
            int testDamage = 50;
            int testShield = 70;

            Character newCharacter = new Character(testHealth, testDamage, testShield);

            Field health = Tester.getField(newCharacter, "health");
            Field damage = Tester.getField(newCharacter, "damage");
            Field shield = Tester.getField(newCharacter, "shield");

            try {
                if (health != null) {
                    health.setAccessible(true);
                    Object value = health.get(newCharacter);
                    if (!(Integer.parseInt(value.toString()) == testHealth)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (damage != null) {
                    damage.setAccessible(true);
                    Object value = damage.get(newCharacter);
                    if (!(Integer.parseInt(value.toString()) == testDamage)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (shield != null) {
                    shield.setAccessible(true);
                    Object value = shield.get(newCharacter);
                    if (!(Integer.parseInt(value.toString()) == testShield)) {
                        System.out.println("FAILED");
                        return;
                    }
                }

                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED7");
            }
        }
        else if (code == 5) {
            Method receiveDamage = Tester.getMethod(character, "receiveDamage");
            Method getHealth = Tester.getMethod(character, "getHealth");
            Method getShield = Tester.getMethod(character, "getShield");

            try {
                int receivedDamage = 100;
                int expectedHealth = 10;

                receiveDamage.invoke(character, receivedDamage);
                int health = Integer.parseInt(getHealth.invoke(character).toString());

                if (expectedHealth == health) {
                    receiveDamage.invoke(character, 20);
                    health = Integer.parseInt(getHealth.invoke(character).toString());

                    if(health == 0) {
                        System.out.println("SUCCESS");
                    }else {
                        System.out.println("FAILED");
                    }
                }else {
                    System.out.println("FAILED");
                }

            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 6) {
            Swordsman newSwordsman = new Swordsman();
            if (newSwordsman instanceof Character)  {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");

        }
        else if (code == 7) {
            int testHealth = 100;
            int testDamage = 10;
            int testShield = 10;

            Swordsman newSwordsman = new Swordsman();

            Field health = Tester.getField(newSwordsman, "health");
            Field damage = Tester.getField(newSwordsman, "damage");
            Field shield = Tester.getField(newSwordsman, "shield");

            try {
                if (health != null) {
                    health.setAccessible(true);
                    Object value = health.get(newSwordsman);
                    if (!(Integer.parseInt(value.toString()) == testHealth)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (damage != null) {
                    damage.setAccessible(true);
                    Object value = damage.get(newSwordsman);
                    if (!(Integer.parseInt(value.toString()) == testDamage)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (shield != null) {
                    shield.setAccessible(true);
                    Object value = shield.get(newSwordsman);
                    if (!(Integer.parseInt(value.toString()) == testShield)) {
                        System.out.println("FAILED");
                        return;
                    }
                }

                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 8) {
            Field hasResurrected = Tester.getField(swordsman, "hasResurrected");

            if((hasResurrected != null && Modifier.isPrivate(hasResurrected.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
        else if (code == 9) {
            Method receiveDamage = Tester.getMethod(swordsman, "receiveDamage");
            Method resurrect = Tester.getMethod(swordsman, "resurrect");

            try {
                System.out.println(receiveDamage.invoke(swordsman, 50));
                System.out.println(resurrect.invoke(swordsman));
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED ");
            }
        }
        else if (code == 10) {
            Paladin newPaladin = new Paladin();
            if (newPaladin instanceof Swordsman)  {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        }
        else if (code == 11) {
            int testHealth = 100;
            int testDamage = 10;
            int testShield = 10;
            boolean testHasResurrected= false;

            Paladin newPaladin = new Paladin();

            Field health = Tester.getField(newPaladin, "health");
            Field damage = Tester.getField(newPaladin, "damage");
            Field shield = Tester.getField(newPaladin, "shield");
            Field hasResurrected = Tester.getField(newPaladin, "hasResurrected");

            try {
                if (health != null) {
                    health.setAccessible(true);
                    Object value = health.get(newPaladin);
                    if (!(Integer.parseInt(value.toString()) == testHealth)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (damage != null) {
                    damage.setAccessible(true);
                    Object value = damage.get(newPaladin);
                    if (!(Integer.parseInt(value.toString()) == testDamage)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (shield != null) {
                    shield.setAccessible(true);
                    Object value = shield.get(newPaladin);
                    if (!(Integer.parseInt(value.toString()) == testShield)) {
                        System.out.println("FAILED");
                        return;
                    }
                }

                if (hasResurrected != null) {
                    hasResurrected.setAccessible(true);
                    Object value = hasResurrected.get(newPaladin);
                    if (!(Boolean.parseBoolean(value.toString()) == testHasResurrected)) {
                        System.out.println("FAILED");
                        return;
                    }
                }
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 12) {
            Method receiveDamage = Tester.getMethod(swordsman, "receiveDamage");
            Method getHealth = Tester.getMethod(swordsman, "getHealth");
            Method getShield = Tester.getMethod(swordsman, "getShield");

            try {
                int receivedDamage = 200;
                int expectedHealth = 10;

                receiveDamage.invoke(swordsman, receivedDamage);
                int health = Integer.parseInt(getHealth.invoke(swordsman).toString());

                if (expectedHealth == health) {
                    receiveDamage.invoke(swordsman, 15);
                    health = Integer.parseInt(getHealth.invoke(swordsman).toString());

                    if (health == 5) {
                        receiveDamage.invoke(swordsman, 30);
                        health = Integer.parseInt(getHealth.invoke(swordsman).toString());

                        if(health == 100) {
                            System.out.println("SUCCESS");
                        }else {
                            System.out.println("FAILED");
                        }
                    }else {
                         System.out.println("FAILED");
                    }
                }else {
                    System.out.println("FAILED");
                }

            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 13) {
            Method resurrect = Tester.getMethod(swordsman, "resurrect");
            Method getHealth = Tester.getMethod(swordsman, "getHealth");
            Method setHealth = Tester.getMethod(swordsman, "setHealth");
            boolean expectedtHasResurrected = true;
            int expectedHealth = 100;

            try {
                setHealth.invoke(swordsman, 0);
                resurrect.invoke(swordsman);
                Field hasResurrectedField = Tester.getField(swordsman, "hasResurrected");

                if (hasResurrectedField != null) {
                    hasResurrectedField.setAccessible(true);
                    Object value = hasResurrectedField.get(swordsman);

                    boolean hasResurrected = Boolean.parseBoolean(value.toString());
                    int health = Integer.parseInt(getHealth.invoke(swordsman).toString());

                    if (expectedtHasResurrected == hasResurrected && expectedHealth == health) {
                        resurrect.invoke(swordsman);
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
    }
}