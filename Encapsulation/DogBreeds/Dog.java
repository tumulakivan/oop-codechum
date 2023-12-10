public class Dog {
    private String breed;
    private int barkCount;

    Dog() {
        this.breed = null;
        this.barkCount = 0;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBarkCount(int count) {
        this.barkCount += count;
    }

    public int getBarkCount() {
        return this.barkCount;
    }

    public boolean hasBarkedALot() {
        if (this.barkCount >= 100)
            return true;
        return false;
    }

    public void bark(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println("Woof");
            this.barkCount++;
        }
    }
}
