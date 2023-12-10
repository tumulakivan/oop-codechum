package lovers;

interface Lover {
    public void setLover(Lover lover);
    public void receiveLove();
    public void giveLove();
}

interface BoyFriend {
    public void giveFlowers(int flowerCount);
}

interface GirlFriend {
    public void receiveFlowers(int flowerCount);
}

public abstract class Friend implements Lover {
    private char gender; // M or F
    private boolean isLoved; // false by default

    public Friend(char gender) {
        this.gender = gender;
        this.isLoved = false;
    }

    public void receiveLove() {
        this.isLoved = true;
    }

    @Override
    public String toString() {
        return this.gender + " - " + String.valueOf(this.isLoved);
    }
}

class MaleFriend extends Friend implements BoyFriend {
    private int flowersGiven; // 0 by default
    private FemaleFriend lover; // null by default
    
    public MaleFriend() {
        super('M');
        this.lover = null;
        this.flowersGiven = 0;
    }

    public void setLover(Lover lover) {
        this.lover = (FemaleFriend) lover;
    }

    public void giveLove() {
        System.out.println("mwamwa");
        this.lover.receiveLove();
    }

    public void giveFlowers(int flowerCount) {
        this.flowersGiven += flowerCount;
        this.lover.receiveFlowers(flowerCount);
    }
}

class FemaleFriend extends Friend implements GirlFriend {
    private int flowersReceived;
    private MaleFriend lover;

    public FemaleFriend() {
        super('F');
        this.lover = null;
        this.flowersReceived = 0;
    }

    public void setLover(Lover lover) {
        this.lover = (MaleFriend) lover;
    }

    public void giveLove() {
        System.out.println("tsuptsup");
        this.lover.receiveLove();
    }

    public void receiveFlowers(int flowerCount) {
        this.flowersReceived += flowerCount;
    }
}