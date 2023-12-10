import java.text.DecimalFormat;

public class Car {
    String color;
    double price;
    char size;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Car(String color, double price, char size) {
        size = Character.toUpperCase(size);
        this.color = color;
        this.price = price;
        this.size = size;
    }

    public String getColor() {
        return this.color;
    }

    public double getPrice() {
        return this.price;
    }

    public char getSize() {
        return this.size;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        if (this.size == 'S')
            return "Car (" + this.color + ") - P" + df.format(this.price) + " - small";
        else if (this.size == 'M')
            return "Car (" + this.color + ") - P" + df.format(this.price) + " - medium";
        else if (this.size == 'L')
            return "Car (" + this.color + ") - P" + df.format(this.price) + " - large";
        return "Invalid car details";
    }
}
