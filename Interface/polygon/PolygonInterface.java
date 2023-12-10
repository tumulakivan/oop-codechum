package polygon;

import java.text.DecimalFormat;

// Start typing your code here
public interface PolygonInterface {
    public DecimalFormat df = new DecimalFormat("#.##");
    public double getArea();
    public double getPerimeter();
}

class Square implements PolygonInterface {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getArea() {
        return Double.parseDouble(df.format(this.side * this.side));
    }

    public double getPerimeter() {
        return Double.parseDouble(df.format(this.side * 4));
    }
}

class Rectangle implements PolygonInterface {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return Double.parseDouble(df.format(this.length * this.width));
    }

    public double getPerimeter() {
        return Double.parseDouble(df.format(2 * (length + width)));
    }
}

class Trapezoid implements PolygonInterface {
    private double a;
    private double b;
    private double c;
    private double d;
    private double height;
    
    public Trapezoid(double a, double b, double c, double d, double height) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.height = height;
    }

    public double getArea() {
        return Double.parseDouble(df.format(((this.a + this.b) / 2) * this.height));
    }

    public double getPerimeter() {
        return Double.parseDouble(df.format(this.a + this.b + this.c + this.d));
    }
}