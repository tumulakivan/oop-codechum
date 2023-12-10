import java.util.*;

public class Main {
    public static void main(String args[]) {

        Swordsman swordsman = new Swordsman();

        // debugging
        System.out.println("current health: " + swordsman.getHealth());
        swordsman.receiveDamage(115);
        System.out.println("current health: " + swordsman.getHealth());
        
        Paladin paladin = new Paladin();

        // NOTE: Uncomment the line below when you want to submit your solution already
         Tester.test(swordsman, paladin);
    }
}