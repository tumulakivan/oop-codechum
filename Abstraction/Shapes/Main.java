import java.util.*;
import java.lang.reflect.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("SQUARE");
        System.out.print("Enter color: ");
        String squareColor = sc.nextLine();
        System.out.print("Enter length: ");
        double squareLength = sc.nextDouble();
        Square square = new Square(squareColor, squareLength);

        sc.nextLine();

        System.out.println("\nRECTANGULAR PRISM:");
        System.out.print("Enter color: ");
        String rpColor = sc.nextLine();
        System.out.print("Enter length: ");
        double rpLength = sc.nextDouble();
        System.out.print("Enter width: ");
        double rpWidth = sc.nextDouble();
        System.out.print("Enter height: ");
        double rpHeight = sc.nextDouble();
        RectangularPrism rectangularPrism = new RectangularPrism(rpColor, rpLength, rpWidth, rpHeight);

        // NOTE: Uncomment the line below before submitting
        Tester.test(square, rectangularPrism);
    }
}

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

        public static void test(Square square, RectangularPrism rectangularPrism) {

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter number of operations: ");
            int operations = scanner.nextInt();

            while(operations-- > 0) {
                System.out.print("Enter code: ");
                int code = scanner.nextInt();
                if(code == 1) {
                     System.out.println("Shape is abstract = " + Modifier.isAbstract(Shape.class.getModifiers()));
                }
                else if(code == 2) {
                    Field name = Tester.getField(square, "name");
                    Field color = Tester.getField(square, "color");
                    Field isFlat = Tester.getField(square, "isFlat");

                    if(
                        (name != null && Modifier.isPrivate(name.getModifiers())) &&
                        (color != null && Modifier.isPrivate(color.getModifiers())) &&
                        (isFlat != null && Modifier.isPrivate(isFlat.getModifiers()))
                    ) {
                        System.out.println("Fields are all private");
                    }
                }
                else if(code == 3) {
                    Method getName = Tester.getMethod(rectangularPrism, "getName");
                    Method getColor = Tester.getMethod(rectangularPrism, "getColor");
                    Method getIsFlat = Tester.getMethod(rectangularPrism, "getIsFlat");

                    try {
                        System.out.println(getName.invoke(rectangularPrism));
                        System.out.println(getColor.invoke(rectangularPrism));
                        System.out.println(getIsFlat.invoke(rectangularPrism));
                    } catch (Exception e) {}
                }
                else if(code == 4) {
                    System.out.println("TwoDShape is abstract = " + Modifier.isAbstract(TwoDShape.class.getModifiers()));
                }
                else if(code == 5) {
                    Field numberOfSides = Tester.getField(square, "numberOfSides");

                    if(numberOfSides != null && Modifier.isPrivate(numberOfSides.getModifiers())) {
                        System.out.println("Fields are all private");
                    }

                    Method getNumberOfSides = Tester.getMethod(square, "getNumberOfSides");
                    try {
                        System.out.println(getNumberOfSides.invoke(square));
                    } catch (Exception e) {}
                }
                else if(code == 6) {
                    try {
                        Method getArea = TwoDShape.class.getDeclaredMethod("getArea");
                        Method getPerimeter = TwoDShape.class.getDeclaredMethod("getPerimeter");

                        if (getArea != null) {
                            System.out.println("Method getArea exists.");
                            System.out.println("getArea is abstract = " + Modifier.isAbstract(getArea.getModifiers()));
                        }
                        if (getPerimeter != null) {
                            System.out.println("Method getPerimeter exists.");
                            System.out.println("getPerimeter is abstract = " + Modifier.isAbstract(getPerimeter.getModifiers()));
                        }
                    } catch (Exception e) {}
                }
                else if(code == 7) {
                    System.out.println("ThreeDShape is abstract = " + Modifier.isAbstract(ThreeDShape.class.getModifiers()));
                }
                else if(code == 8) {
                    try {
                        Method getSurfaceArea = ThreeDShape.class.getDeclaredMethod("getSurfaceArea");
                        Method getVolume = ThreeDShape.class.getDeclaredMethod("getVolume");

                        if (getSurfaceArea != null) {
                            System.out.println("Method getSurfaceArea exists.");
                            System.out.println("getSurfaceArea is abstract = " + Modifier.isAbstract(getSurfaceArea.getModifiers()));
                        }
                        if (getVolume != null) {
                            System.out.println("Method getVolume exists.");
                            System.out.println("getVolume is abstract = " + Modifier.isAbstract(getVolume.getModifiers()));
                        }
                    } catch (Exception e) {}
                }
                else if(code == 9) {
                    Field lengthOfSide = Tester.getField(square, "lengthOfSide");

                    if(lengthOfSide != null && Modifier.isPrivate(lengthOfSide.getModifiers())) {
                        System.out.println("Fields are all private");
                    }

                    Method getLengthOfSide = Tester.getMethod(square, "getLengthOfSide");
                    try {
                        System.out.println(getLengthOfSide.invoke(square));
                    } catch (Exception e) {}
                }
                else if(code == 10) {
                    Method getArea = Tester.getMethod(square, "getArea");

                    try {
                        System.out.println("Area = " + getArea.invoke(square));
                    } catch (Exception e) {}
                }
                else if(code == 11) {
                    Method getPerimeter = Tester.getMethod(square, "getPerimeter");

                    try {
                        System.out.println("Perimeter = " + getPerimeter.invoke(square));
                    } catch (Exception e) {}
                }
                else if(code == 12) {
                    try {
                        Square newSquare = new Square("Red", 5);

                        Field name = Tester.getField(newSquare, "name");
                        Field numberOfSides = Tester.getField(newSquare, "numberOfSides");

                        if(newSquare != null && name != null) {
                            name.setAccessible(true);
                            Object value1 = name.get(newSquare);
                            System.out.println("Name = " + value1);
                        }

                        if(newSquare != null && numberOfSides != null) {
                            numberOfSides.setAccessible(true);
                            Object value2 = numberOfSides.get(newSquare);
                            System.out.println("Number of sides = " + value2);
                        }
                    } catch (Exception e) {}
                }
                else if(code == 13) {
                    Field length = Tester.getField(rectangularPrism, "length");
                    Field width = Tester.getField(rectangularPrism, "width");
                    Field height = Tester.getField(rectangularPrism, "height");

                    if(
                        (length != null && Modifier.isPrivate(length.getModifiers())) &&
                        (width != null && Modifier.isPrivate(width.getModifiers())) &&
                        (height != null && Modifier.isPrivate(height.getModifiers()))
                    ) {
                        System.out.println("Fields are all private");
                    }

                    Method getLength = Tester.getMethod(rectangularPrism, "getLength");
                    Method getWidth = Tester.getMethod(rectangularPrism, "getWidth");
                    Method getHeight = Tester.getMethod(rectangularPrism, "getHeight");

                    try {
                        System.out.println(getLength.invoke(rectangularPrism));
                        System.out.println(getWidth.invoke(rectangularPrism));
                        System.out.println(getHeight.invoke(rectangularPrism));
                    } catch (Exception e) {}
                }
                else if(code == 14) {
                    Method getSurfaceArea = Tester.getMethod(rectangularPrism, "getSurfaceArea");

                    try {
                        System.out.println("Surface area = " + getSurfaceArea.invoke(rectangularPrism));
                    } catch (Exception e) {}
                }
                else if(code == 15) {
                    Method getVolume = Tester.getMethod(rectangularPrism, "getVolume");

                    try {
                        System.out.println("Volume = " + getVolume.invoke(rectangularPrism));
                    } catch (Exception e) {}
                }
                else if(code == 16) {
                    try {
                        RectangularPrism newRectangularPrism = new RectangularPrism("Red", 5, 5, 5);

                        Field name = Tester.getField(newRectangularPrism, "name");

                        if(newRectangularPrism != null && name != null) {
                            name.setAccessible(true);
                            Object value1 = name.get(newRectangularPrism);
                            System.out.println("Name = " + value1);
                        }
                    } catch (Exception e) {}
                }
        }
    }
}