package living;

public interface LivingBeing {
    public void eat();
    public void grow();
    public void grow(int n);
}

class Animal implements LivingBeing {
    public Animal() {

    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void grow() {
        System.out.println("Animal is growing");
    }

    public void grow(int n) {
        for(int i = 0; i < n; i++) 
            System.out.println("Animal is growing");
    }
}

class Human implements LivingBeing {
    public Human() {

    }

    public void eat() {
        System.out.println("Human is eating");
    }

    public void grow() {
        System.out.println("Human is growing");
    }

    public void grow(int n) {
        for(int i = 0; i < n; i++) 
            System.out.println("Human is growing");
    }
}

class Dog extends Animal {
    public Dog() {

    }

    public void eat() {
        System.out.println("Animal is eating");
    }

    public void grow() {
        System.out.println("Animal is growing");
    }

    public void grow(int n) {
        for(int i = 0; i < n; i++) 
            System.out.println("Animal is growing");
    }

    public void bark() {
        System.out.println("Woof");
    }
}

