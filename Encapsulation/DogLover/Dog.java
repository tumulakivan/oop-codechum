public class Dog {
    private String breed = "Golden Retriever";
    char gender;
    String color;

    public Dog(char gender, String color) {
        String gold = "gold";
        String brown = "brown";
        if (!color.equals(gold) && !color.equals(brown))
            color = gold;

        this.gender = gender;
        this.color = color;
    }

    public String getBreed() {
        return this.breed;
    }

    public char getGender() {
        this.gender = Character.toLowerCase(this.gender);
        return this.gender;
    }

    public String getColor() {
        return this.color;
    }

    public void bark(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Woof");
        }
    }

    @Override
    public String toString() {
        if (this.getGender() == 'm')
            return "My male, " + this.getColor() + " " + this.getBreed();
        return "My female, " + this.getColor() + " " + this.getBreed();
    }
}
