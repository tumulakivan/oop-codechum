public class Phone {
    private boolean hasUnlimitedCalls;
    private boolean hasUnlimitedTexts;
    private int internetMB;

    public Phone() {
        this.hasUnlimitedCalls = false;
        this.hasUnlimitedTexts = false;
        this.internetMB = 0;
    }

    public void load(CellphoneLoad load) {
        this.hasUnlimitedCalls = load.getHasUnlimitedCalls();
        this.hasUnlimitedTexts = load.getHasUnlimitedTexts();
        this.internetMB += load.getInternetMB();
    }

    @Override
    public String toString() {
        return String.format("Has unlimited calls = %b, Has unlimited texts = %b, internet MB = %d", this.hasUnlimitedCalls, this.hasUnlimitedTexts, this.internetMB);
    }
}