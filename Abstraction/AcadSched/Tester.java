package AcadSched;

import java.util.*;
import java.lang.reflect.*;
@SuppressWarnings("deprecation")

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

    public static void test(ScheduleStructure scheduleStructure) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter code: ");
        int code = scanner.nextInt();
        if (code == 1) {
            Field currentPeriodNumber = Tester.getField(scheduleStructure, "currentPeriodNumber");

            if ((currentPeriodNumber != null && Modifier.isPrivate(currentPeriodNumber.getModifiers()))) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILED");
            }
        }
         else if (code == 2) {
            if(Modifier.isAbstract(ScheduleStructure.class.getModifiers())) {
                System.out.println("SUCCESS");
            }else {
                System.out.println("FAILED");
            }
        }
        else if (code == 3) {
            try {
                Method getDaysRemaining = ScheduleStructure.class.getDeclaredMethod("getDaysRemaining");
                Method getDaysUntilNextPeriod = ScheduleStructure.class.getDeclaredMethod("getDaysUntilNextPeriod");

                if (getDaysRemaining != null) {
                    if(!(Modifier.isAbstract(getDaysRemaining.getModifiers()))) {
                        System.out.println("FAILED");
                        return;
                    }
                }else {
                     System.out.println("FAILED");
                     return;
                }

                if (getDaysUntilNextPeriod != null) {
                    if(!(Modifier.isAbstract(getDaysUntilNextPeriod.getModifiers()))) {
                        System.out.println("FAILED");
                        return;
                    }
                }else {
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
           Method getCurrentPeriodNumber = Tester.getMethod(scheduleStructure, "getCurrentPeriodNumber");
           Method getDaysRemaining = Tester.getMethod(scheduleStructure, "getDaysRemaining");
           Method getDaysUntilNextPeriod = Tester.getMethod(scheduleStructure, "getDaysUntilNextPeriod");

            try {
                System.out.println(getCurrentPeriodNumber.invoke(scheduleStructure));
                System.out.println("Days Remaining: " + getDaysRemaining.invoke(scheduleStructure));
                System.out.println("Days Until Next Period: " + getDaysUntilNextPeriod.invoke(scheduleStructure));
                System.out.println("SUCCESS");
            }
            catch(Exception e) {
                System.out.println("FAILED");
            }
        }
        else if (code == 5) {
            if (scheduleStructure instanceof ScheduleStructure) {
                System.out.println("SUCCESS");
            }
            else {
                System.out.println("FAILED");
            }
        }
        else if (code == 6) {
            if (scheduleStructure instanceof SchoolYearSchedule) {
                Field start = Tester.getField(scheduleStructure, "start");
                Field end = Tester.getField(scheduleStructure, "end");
                Field currentDate = Tester.getField(scheduleStructure, "currentDate");

                if ((start != null && Modifier.isPrivate(start.getModifiers())) &&
                    (end != null && Modifier.isPrivate(end.getModifiers())) &&
                    (currentDate != null && Modifier.isPrivate(currentDate.getModifiers()))
                ) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILED");
                }
            }
            else if (scheduleStructure instanceof SemestralSchedule) {
                Field pairs = Tester.getField(scheduleStructure, "pairs");
                Field currentDate = Tester.getField(scheduleStructure, "currentDate");

                if ((pairs != null && Modifier.isPrivate(pairs.getModifiers())) &&
                    (currentDate != null && Modifier.isPrivate(currentDate.getModifiers()))
                ) {
                    System.out.println("SUCCESS");
                } else {
                    System.out.println("FAILED");
                }
            }
        }
        else if (code == 7) {
            if (scheduleStructure instanceof SchoolYearSchedule) {
                Date testStart = new Date(2022, 8, 1);
                Date testEnd = new Date(2023, 6, 1);
                Date testCurrentDate = new Date(2022, 10, 1);
                int testCurrentPeriodNumber = 1;
                SchoolYearSchedule newSchoolYearSchedule = new SchoolYearSchedule(testCurrentDate, testStart, testEnd);

                try {
                    Field start = Tester.getField(newSchoolYearSchedule, "start");
                    Field end = Tester.getField(newSchoolYearSchedule, "end");
                    Field currentDate = Tester.getField(newSchoolYearSchedule, "currentDate");
                    Field currentPeriodNumber = Tester.getField(newSchoolYearSchedule, "currentPeriodNumber");

                    if(start != null) {
                        start.setAccessible(true);
                        Object value = start.get(newSchoolYearSchedule);
                        if (!(value.toString().equals(testStart.toString()))) {
                            System.out.println("FAILED");
                            return;
                        }
                    } else {
                        System.out.println("FAILED");
                        return;
                    }

                    if(end != null) {
                        end.setAccessible(true);
                        Object value = end.get(newSchoolYearSchedule);
                        if (!(value.toString().equals(testEnd.toString()))) {
                            System.out.println("FAILED");
                            return;
                        }
                    } else {
                        System.out.println("FAILED");
                        return;
                    }

                    if(currentDate != null) {
                        currentDate.setAccessible(true);
                        Object value = currentDate.get(newSchoolYearSchedule);
                        if (!(value.toString().equals(testCurrentDate.toString()))) {
                            System.out.println("FAILED");
                            return;
                        }
                    } else {
                        System.out.println("FAILED");
                        return;
                    }

                    if(currentPeriodNumber != null) {
                        currentPeriodNumber.setAccessible(true);
                        Object value = currentPeriodNumber.get(newSchoolYearSchedule);
                        if (!(Integer.parseInt(value.toString()) == testCurrentPeriodNumber)) {
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
        }
        else if (code == 8) {
            if (scheduleStructure instanceof SemestralSchedule) {
                Date testCurrentDate = new Date(2022, 10, 1);
                Date testSem1Start = new Date(2022, 8, 1);
                Date testSem1End = new Date(2022, 11, 1);
                Date testSem2Start = new Date(2023, 1, 1);
                Date testSem2End = new Date(2023, 6, 1);
                Date[][] testPairs = new Date[2][2];
                testPairs[0] = new Date[]{testSem1Start, testSem1End};
                testPairs[1] = new Date[]{testSem2Start, testSem2End};
                int testCurrentPeriodNumber = 1;

                SemestralSchedule newSemestralSchedule = new SemestralSchedule(testCurrentDate, testSem1Start, testSem1End, testSem2Start, testSem2End, testCurrentPeriodNumber);
                try {
                    Field pairs = Tester.getField(newSemestralSchedule, "pairs");
                    Field currentDate = Tester.getField(newSemestralSchedule, "currentDate");
                    Field currentPeriodNumber = Tester.getField(newSemestralSchedule, "currentPeriodNumber");

                if(pairs != null) {
                    pairs.setAccessible(true);
                    Object value = pairs.get(newSemestralSchedule);
                    Date[][] dates = (Date[][]) value;

                    if (!(dates[0][0] == testPairs[0][0] &&
                        dates[0][1] == testPairs[0][1] &&
                        dates[1][0] == testPairs[1][0] &&
                        dates[1][1] == testPairs[1][1])
                    ) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if(currentDate != null) {
                    currentDate.setAccessible(true);
                    Object value = currentDate.get(newSemestralSchedule);
                    if (!(value.toString().equals(testCurrentDate.toString()))) {
                        System.out.println("FAILED");
                        return;
                    }
                } else {
                    System.out.println("FAILED");
                    return;
                }

                if(currentPeriodNumber != null) {
                    currentPeriodNumber.setAccessible(true);
                    Object value = currentPeriodNumber.get(newSemestralSchedule);
                    if (!(Integer.parseInt(value.toString()) == testCurrentPeriodNumber)) {
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
        }
        else if (code == 9) {
            Date testCurrentDate = new Date(2022, 10, 1);
            Date testStart = new Date(2022, 8, 1);
            Date testEnd = new Date(2023, 6, 1);
            SchoolYearSchedule newSchoolYearSchedule = new SchoolYearSchedule(testCurrentDate, testStart, testEnd);

            Method getDaysRemaining = Tester.getMethod(newSchoolYearSchedule, "getDaysRemaining");
            try {
                if (newSchoolYearSchedule instanceof SchoolYearSchedule) {
                    int expected = 242;
                    if (expected == Integer.parseInt(getDaysRemaining.invoke(newSchoolYearSchedule).toString())) {
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
        else if (code == 10) {
            Date testCurrentDate = new Date(2022, 10, 1);
            Date testStart = new Date(2022, 8, 1);
            Date testEnd = new Date(2023, 6, 1);
            SchoolYearSchedule newSchoolYearSchedule = new SchoolYearSchedule(testCurrentDate, testStart, testEnd);

            Method getDaysUntilNextPeriod = Tester.getMethod(newSchoolYearSchedule, "getDaysUntilNextPeriod");
            try {
                if (newSchoolYearSchedule instanceof SchoolYearSchedule) {
                    int expected = 304;
                    if (expected == Integer.parseInt(getDaysUntilNextPeriod.invoke(newSchoolYearSchedule).toString())) {
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
        else if (code == 11) {
            Date testCurrentDate = new Date(2022, 9, 1);
            Date testSem1Start = new Date(2022, 8, 1);
            Date testSem1End = new Date(2022, 11, 1);
            Date testSem2Start = new Date(2023, 1, 1);
            Date testSem2End = new Date(2023, 6, 1);
            int testCurrentPeriodNumber = 1;

            SemestralSchedule newSemestralSchedule = new SemestralSchedule(testCurrentDate, testSem1Start, testSem1End, testSem2Start, testSem2End, testCurrentPeriodNumber);

            Method getDaysRemaining = Tester.getMethod(newSemestralSchedule, "getDaysRemaining");
            try {
                if (newSemestralSchedule instanceof SemestralSchedule) {
                    int expected = 61;
                    if (expected == Integer.parseInt(getDaysRemaining.invoke(newSemestralSchedule).toString())) {
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
        else if (code == 12) {
            Date testCurrentDate = new Date(2023, 4, 1);
            Date testSem1Start = new Date(2022, 8, 1);
            Date testSem1End = new Date(2022, 11, 1);
            Date testSem2Start = new Date(2023, 1, 1);
            Date testSem2End = new Date(2023, 6, 1);
            int testCurrentPeriodNumber = 2;

            SemestralSchedule newSemestralSchedule = new SemestralSchedule(testCurrentDate, testSem1Start, testSem1End, testSem2Start, testSem2End, testCurrentPeriodNumber);

            Method getDaysRemaining = Tester.getMethod(newSemestralSchedule, "getDaysRemaining");
            try {
                if (newSemestralSchedule instanceof SemestralSchedule) {
                    int expected = 61;
                    if (expected == Integer.parseInt(getDaysRemaining.invoke(newSemestralSchedule).toString())) {
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
         else if (code == 13) {
            Date testCurrentDate = new Date(2022, 9, 1);
            Date testSem1Start = new Date(2022, 8, 1);
            Date testSem1End = new Date(2022, 11, 1);
            Date testSem2Start = new Date(2023, 1, 1);
            Date testSem2End = new Date(2023, 6, 1);
            int testCurrentPeriodNumber = 1;

            SemestralSchedule newSemestralSchedule = new SemestralSchedule(testCurrentDate, testSem1Start, testSem1End, testSem2Start, testSem2End, testCurrentPeriodNumber);

            Method getDaysUntilNextPeriod = Tester.getMethod(newSemestralSchedule, "getDaysUntilNextPeriod");
            try {
                if (newSemestralSchedule instanceof SemestralSchedule) {
                    int expected = 123;
                    if (expected == Integer.parseInt(getDaysUntilNextPeriod.invoke(newSemestralSchedule).toString())) {
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
        else if (code == 14) {
            Date testCurrentDate = new Date(2023, 4, 1);
            Date testSem1Start = new Date(2022, 8, 1);
            Date testSem1End = new Date(2022, 11, 1);
            Date testSem2Start = new Date(2023, 1, 1);
            Date testSem2End = new Date(2023, 6, 1);
            int testCurrentPeriodNumber = 2;

            SemestralSchedule newSemestralSchedule = new SemestralSchedule(testCurrentDate, testSem1Start, testSem1End, testSem2Start, testSem2End, testCurrentPeriodNumber);
            Method getDaysUntilNextPeriod = Tester.getMethod(newSemestralSchedule, "getDaysUntilNextPeriod");
            try {
                if (newSemestralSchedule instanceof SemestralSchedule) {
                    int expected = 123;
                    if (expected == Integer.parseInt(getDaysUntilNextPeriod.invoke(newSemestralSchedule).toString())) {
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