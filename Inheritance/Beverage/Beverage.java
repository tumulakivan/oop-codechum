import java.text.DecimalFormat;

public class Beverage {
    private String name;
    private int volume;
    private boolean isChilled;

    public static final DecimalFormat df = new DecimalFormat("0.0");

    public Beverage(String name, int volume, boolean isChilled) {
        this.name = name;
        this.volume = volume;
        this.isChilled = isChilled;
    }

    public boolean isEmpty() {
        if (this.volume == 0)
            return true;

        return false;
    }

    public String getName() {
        return this.name;
    }

    public int getVolume() {
        return this.volume;
    }

    public boolean getIsChilled() {
        return this.isChilled;
    }

    @Override
    public String toString() {
        String chill;
        if (!this.isChilled)
            chill = "is not chilled anymore";
        chill = "is still chilled";
        return this.name + " (" + volume + "mL) " + chill;
    }
}

final class Water extends Beverage {
    private String type; // only Regular, Purified, Distilled.

    public Water(int volume, boolean isChilled, String type) {
        super("Water", volume, isChilled);
        this.type = type;
    }

    public Water(int volume, boolean isChilled) {
        super("Water", volume, isChilled);
        this.type = "Regular";
    }

    public String getType() {
        return this.type;
    }
}

final class Beer extends Beverage {
    private double alcoholicContent;

    public Beer(int volume, boolean isChilled, double alcoholicContent) {
        super("Beer", volume, isChilled);
        this.alcoholicContent = alcoholicContent;
    }

    public double getAlcoholicContent() {
        return this.alcoholicContent;
    }

    public String getType() {
        if (this.alcoholicContent > 0.0 && this.alcoholicContent < 0.03)
            return "Flavored";
        else if (this.alcoholicContent >= 0.03 && this.alcoholicContent < 0.06)
            return "Regular";

        return "Strong";
    }

    @Override
    public String toString() {
        return super.toString() + " (" + df.format(this.alcoholicContent * 100) + "% alcoholic content)";
    }
}