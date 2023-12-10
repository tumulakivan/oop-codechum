abstract class Shape {
    private String name;
    private String color;
    private boolean isFlat;

    public Shape(String name, String color, boolean isFlat) {
        this.name = name;
        this.color = color;
        this.isFlat = isFlat;
    }

    public String getName() {
        return this.name;
    }

    public String getColor() {
        return this.color;
    }

    public boolean getIsFlat() {
        return this.isFlat;
    }
    
    public void setColor(String color) {
        this.color = color;
    }
}

abstract class TwoDShape extends Shape {
    private int numberOfSides;

    public TwoDShape(String name, String color, int numberOfSides) {
        super(name, color, true);
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return this.numberOfSides;
    }

    public abstract double getArea();
    public abstract double getPerimeter();
}

abstract class ThreeDShape extends Shape {
    public ThreeDShape(String name, String color) {
        super(name, color, false);
    }

    public abstract double getSurfaceArea();
    public abstract double getVolume();
}