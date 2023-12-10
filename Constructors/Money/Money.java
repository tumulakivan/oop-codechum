public class Money {
    public int amount;
    public String denomination;

    Money() {
        this.amount = 0;
        this.denomination = "Unknown";
    }

    Money(int amount) {
        this.amount = amount;
        this.denomination = "Unknown";
    }

    Money(int amount, String denomination) {
        this.amount = amount;
        this.denomination = denomination;
    }
}
