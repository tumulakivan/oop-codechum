public abstract class FamilyMember {
    private String type;

    public FamilyMember(String type) {
        this.type = type;
    }

    abstract public void greet();

    @Override
    public String toString() {
        return "Family Member [" + this.type + "]";
    }
}

class Father extends FamilyMember {
    public Father() {
        super("Father");
    }

    @Override
    public void greet() {
        System.out.println("I am your father");
    }
}

class Mother extends FamilyMember {
    public Mother() {
        super("Mother");
    }

    @Override
    public void greet() {
        System.out.println("Mother knows best");
    }
}

class Son extends FamilyMember {
    public Son() {
        super("Son");
    }

    @Override
    public void greet() {
        System.out.println("My father and mother loves me");
    }
}