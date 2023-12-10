import java.text.DecimalFormat;

public class Number {
    private int value;

    public Number(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

class WholeNumber extends Number {
    public WholeNumber(int value) {
        super(value);
    }

    public void multiply(WholeNumber wholeNumber) {
        setValue(this.getValue() * wholeNumber.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }
}

class DecimalNumber extends Number {
    private int decimalPlaces;

    public DecimalNumber(int value, int decimalPlaces) {
        super(value);
        this.decimalPlaces = decimalPlaces;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public int getDecimalPlaces() {
        return this.decimalPlaces;
    }

    public void multiply(DecimalNumber decimalNumber) {
        this.setValue(decimalNumber.getValue() * this.getValue());
        int dP = this.getDecimalPlaces() + decimalNumber.getDecimalPlaces();
        this.setDecimalPlaces(dP);
    }

    @Override
    public String toString() {
        String pattern = "0." + "0".repeat(this.getDecimalPlaces());
        DecimalFormat df = new DecimalFormat(pattern);

        float val = (float) this.getValue();
        for (int i = 0; i < this.getDecimalPlaces(); i++) {
            val = (float) (val * 0.1);
        }
        String formatValue = df.format(val);

        return formatValue;
    }
}