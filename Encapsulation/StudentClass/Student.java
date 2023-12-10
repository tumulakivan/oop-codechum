public class Student {
    String firstName;
    String lastName;
    int age;
    boolean isMinor;

    public Student(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getName() {
        return this.lastName + ", " + this.firstName;
    }

    public void increaseAge() {
        this.age++;
    };

    @Override
    public String toString() {
        if (this.age < 18)
            this.isMinor = true;
        else
            this.isMinor = false;

        if (this.isMinor)
            return this.lastName + ", " + this.firstName + " - " + this.age + " - minor";

        return this.lastName + ", " + this.firstName + " - " + this.age + " - adult";
    }
}
