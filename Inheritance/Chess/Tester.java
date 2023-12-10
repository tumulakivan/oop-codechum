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

    public static void test(ChessPiece chessPiece, boolean isTwoMoves) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field type = Tester.getField(chessPiece, "type");
            Field isWhite = Tester.getField(chessPiece, "isWhite");

            if ((type != null && Modifier.isPrivate(type.getModifiers())) &&
                    (isWhite != null && Modifier.isPrivate(isWhite.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        } else if (code == 2) {
            Method getType = Tester.getMethod(chessPiece, "getType");
            Method getIsWhite = Tester.getMethod(chessPiece, "getIsWhite");

            try {
                System.out.println(getType.invoke(chessPiece));
                System.out.println(getIsWhite.invoke(chessPiece));
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 3) {
            String testType = "Test";
            boolean testIsWhite = true;
            ChessPiece newChessPiece = new ChessPiece(testType, testIsWhite);
            Field type = Tester.getField(newChessPiece, "type");
            Field isWhite = Tester.getField(newChessPiece, "isWhite");

            try {
                if (type != null) {
                    type.setAccessible(true);
                    Object value = type.get(newChessPiece);

                    if (!value.toString().equals(testType)) {
                        System.out.println("FAILED");
                        return;
                    }

                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (isWhite != null) {
                    isWhite.setAccessible(true);
                    Object value = isWhite.get(newChessPiece);
                    if (!(Boolean.parseBoolean(value.toString()) == testIsWhite)) {
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
        } else if (code == 4) {
            String testType = "Pawn";
            boolean testHasMoved = false;
            boolean testIsWhite = true;
            Pawn newPawn = new Pawn(testIsWhite);
            Field hasMoved = Tester.getField(newPawn, "hasMoved");
            Field type = Tester.getField(newPawn, "type");

            try {
                if (hasMoved != null) {
                    hasMoved.setAccessible(true);
                    Object value = hasMoved.get(newPawn);
                    if (!(Boolean.parseBoolean(value.toString()) == testHasMoved)) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if (type != null) {
                    type.setAccessible(true);
                    Object value = type.get(newPawn);
                    if (!value.toString().equals(testType)) {
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
            Method move = Tester.getMethod(chessPiece, "move");

            try {
                System.out.println(move.invoke(chessPiece, true));
                System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 6) {
            try {
                if (chessPiece instanceof ChessPiece) {
                    Field hasMoved = Tester.getField(chessPiece, "hasMoved");
                    if ((hasMoved != null && Modifier.isPrivate(hasMoved.getModifiers()))) {
                        System.out.println("SUCCESS");
                    }
                } else {
                    System.out.println("FAILED");
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 7) {
            Pawn newPawn = new Pawn(true);

            if (newPawn instanceof ChessPiece) {
                System.out.println("SUCCESS");
                return;
            }
            System.out.println("FAILED");
        } else if (code == 8) {
            try {
                if (chessPiece instanceof ChessPiece) {

                    Method testMove1 = Tester.getMethod(chessPiece, "move");
                    testMove1.invoke(chessPiece, isTwoMoves);

                    Field hasMoved1 = Tester.getField(chessPiece, "hasMoved");
                    hasMoved1.setAccessible(true);
                    Object value1 = hasMoved1.get(chessPiece);
                    boolean testHasMoved1 = Boolean.parseBoolean(value1.toString());

                    boolean expected = true;

                    if (!(expected == testHasMoved1)) {
                        System.out.println("FAILED");
                    }

                    Method testMove2 = Tester.getMethod(chessPiece, "move");
                    testMove2.invoke(chessPiece, !isTwoMoves);

                    Field hasMoved2 = Tester.getField(chessPiece, "hasMoved");
                    hasMoved2.setAccessible(true);
                    Object value2 = hasMoved2.get(chessPiece);
                    boolean testHasMoved2 = Boolean.parseBoolean(value2.toString());

                    if (!(expected == testHasMoved2)) {
                        System.out.println("FAILED");
                    }

                    System.out.println("SUCCESS");
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        } else if (code == 9) {
            try {
                Method getIsWhite = Tester.getMethod(chessPiece, "getIsWhite");
                boolean testIsWhite = Boolean.parseBoolean(getIsWhite.invoke(chessPiece).toString());

                Field hasMoved = Tester.getField(chessPiece, "hasMoved");
                hasMoved.setAccessible(true);
                Object value = hasMoved.get(chessPiece);
                boolean testHasMoved = Boolean.parseBoolean(value.toString());

                String expected;

                if (chessPiece instanceof ChessPiece) {
                    expected = (testIsWhite ? "White" : "Black") + " pawn has "
                            + (testHasMoved ? "already moved" : "not yet moved");

                    if (expected.equals(chessPiece.toString())) {
                        System.out.println("SUCCESS");
                        return;
                    } else {
                        System.out.println("FAILED");
                    }
                }
            } catch (Exception e) {
                System.out.println("FAILED");
            }
        }
    }
}