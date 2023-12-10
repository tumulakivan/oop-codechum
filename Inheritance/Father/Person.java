public class Person {
    private String name;
    private int age;
    private char gender;

    public Person(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public char getGender() {
        return this.gender;
    }

    public boolean isMinor() {
        return this.age < 18;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.age + " - " + this.gender;
    }
}

class Father extends Person {
    public Father(String name, int age) {
        super(name, age, 'M');
    }

    public void introduceWithStyle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            System.out.println("I am your father");
        }
    }
}

final class Son extends Father {
    public Son(String name, int age) {
        super(name, age);
    }

    public Son(int age) {
        super("Unknown", age);
    }

    @Override
    public void introduceWithStyle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            System.out.println("I am your son");
        }
    }
}