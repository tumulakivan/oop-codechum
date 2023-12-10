public class RectangularPrism extends ThreeDShape {
    private double length;
    private double width;
    private double height;

    public RectangularPrism(String color, double length, double width, double height) {
        super("Rectangular Prism", color);
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public double getLength() {
        return this.length;
    }

    public double getWidth() {
        return this.width;
    }

    public double getHeight() {
        return this.height;
    }

    @Override
    public double getSurfaceArea() {
        return 2 * ((this.width * this.height) + (this.height * this.length) + (this.height * this.width));
    }

    @Override
    public double getVolume() {
        return this.width * this.height * this.length;
    }
}