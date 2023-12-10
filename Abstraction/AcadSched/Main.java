package AcadSched;

import java.util.Date;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Main {

    public static void main(String[] args) {
        // Test SchoolYearSchedule class
        Date currentDate = new Date();
        Date start = new Date(currentDate.getYear(), 0, 1); // Start of the current year
        Date end = new Date(currentDate.getYear(), 11, 31); // End of the current year
        SchoolYearSchedule schoolYearSchedule = new SchoolYearSchedule(currentDate, start, end);
        testSchedule(schoolYearSchedule);
        testInheritance(schoolYearSchedule, ScheduleStructure.class);

        // Test SemestralSchedule class
        Date sem1Start = new Date(currentDate.getYear(), 0, 1);
        Date sem1End = new Date(currentDate.getYear(), 5, 30);
        Date sem2Start = new Date(currentDate.getYear(), 6, 1);
        Date sem2End = new Date(currentDate.getYear(), 11, 31);
        SemestralSchedule semestralSchedule = new SemestralSchedule(currentDate, sem1Start, sem1End, sem2Start, sem2End, 1);
        testSchedule(semestralSchedule);
        testInheritance(semestralSchedule, ScheduleStructure.class);

        // Check if fields in ScheduleStructure are private
        testFieldPrivacy(ScheduleStructure.class);

        // Test if ScheduleStructure class is abstract
        testIfClassIsAbstract(ScheduleStructure.class);
    }

    private static void testSchedule(ScheduleStructure schedule) {
        System.out.println("Days Remaining: " + schedule.getDaysRemaining());
        System.out.println("Days Until Next Period: " + schedule.getDaysUntilNextPeriod());
    }

    private static void testInheritance(Object obj, Class<?> parentClass) {
        if (parentClass.isAssignableFrom(obj.getClass())) {
            System.out.println(obj.getClass().getSimpleName() + " is a subclass of " + parentClass.getSimpleName());
        } else {
            System.out.println(obj.getClass().getSimpleName() + " is not a subclass of " + parentClass.getSimpleName());
        }
    }

    private static void testFieldPrivacy(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        boolean allFieldsPrivate = true;

        for (Field field : fields) {
            if (!Modifier.isPrivate(field.getModifiers())) {
                allFieldsPrivate = false;
                System.out.println("Field '" + field.getName() + "' in " + clazz.getSimpleName() + " is not private");
            }
        }

        if (allFieldsPrivate) {
            System.out.println("All fields in " + clazz.getSimpleName() + " are private");
        } else {
            System.out.println("Not all fields in " + clazz.getSimpleName() + " are private");
        }
    }

    private static void testIfClassIsAbstract(Class<?> clazz) {
        boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
        System.out.println(clazz.getSimpleName() + " is abstract: " + isAbstract);
    }
}