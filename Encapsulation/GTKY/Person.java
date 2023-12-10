public class Person {
    private String name;
    private int age;
    private char gender;

    Person() {
        this.name = "";
        this.age = 0;
        this.gender = 'M';
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}