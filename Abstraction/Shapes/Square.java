public class Square extends TwoDShape {
    private double lengthOfSide;

    public Square(String color, double lengthOfSide) {
        super("Square", color, 4);
        this.lengthOfSide = lengthOfSide;
    }

    public double getLengthOfSide() {
        return this.lengthOfSide;
    }
    
    @Override
    public double getArea() {
        return this.lengthOfSide * this.lengthOfSide;
    }

    @Override
    public double getPerimeter() {
        return 4 * this.lengthOfSide;
    }

}