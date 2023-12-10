public class Character {
    private int health;
    private int damage;
    private int shield;

    public Character(int health, int damage, int shield) {
        this.health = health;
        this.damage = damage;
        this.shield = shield;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return this.health;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getShield() {
        return this.shield;
    }

    public void receiveDamage(int damage) {
        int finalDamage = this.shield - damage;

        if(finalDamage < 0)
            this.health += finalDamage;

        if(this.health <= 0) {
            System.out.println("Character has died");
        }
    }
}

class Swordsman extends Character {
    public Swordsman() {
        super(100, 10, 10);
    }
}

class Paladin extends Swordsman {
    private boolean hasResurrected;

    public Paladin() {
        super();
        this.hasResurrected = false;
    }

    public void resurrect() {
        if(!hasResurrected) {
            this.hasResurrected = true;
            this.setHealth(100);
            return;
        }
        System.out.println("Paladin has died");
    }

    @Override
    public void receiveDamage(int damage) {
        int finalDamage;

        if(damage % 2 == 0)
            damage = damage / 2;

        finalDamage = this.getShield() - damage;
        if(finalDamage < 0)
            this.setHealth(this.getHealth() + finalDamage);

        if(this.getHealth() <= 0) {
            this.resurrect();
        }
    }
}