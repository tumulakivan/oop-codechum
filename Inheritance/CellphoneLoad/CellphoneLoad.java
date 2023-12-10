public class CellphoneLoad {
    private boolean hasUnlimitedCalls;
    private boolean hasUnlimitedTexts;
    private int internetMB;

    public CellphoneLoad(boolean hasUnlimitedCalls, boolean hasUnlimitedTexts, int internetMB) {
        this.hasUnlimitedCalls = hasUnlimitedCalls;
        this.hasUnlimitedTexts = hasUnlimitedTexts;
        this.internetMB = internetMB;
    }

    public boolean getHasUnlimitedCalls() {
        return this.hasUnlimitedCalls;
    }

    public boolean getHasUnlimitedTexts() {
        return this.hasUnlimitedTexts;
    }

    public int getInternetMB() {
        return this.internetMB;
    }
}

class CITLoad extends CellphoneLoad {
    public CITLoad() {
        super(true, true, 1000);
    }
}

class CTLoad extends CellphoneLoad {
    public CTLoad() {
        super(true, true, 0);
    }
}

class ILoad extends CellphoneLoad {
    public ILoad() {
        super(false, false, 2000);
    }
}