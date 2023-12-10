import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int type, volume;
        double alc;
        char chill;
        String waterType;
        boolean chillStatus;

        System.out.print("Select type (1 - Water, 2 - Beer): ");
        type = sc.nextInt();
        System.out.print("Enter volume: ");
        volume = sc.nextInt();
        System.out.print("Is chilled (Y - yes, N - no): ");
        sc.nextLine();
        chill = sc.next().charAt(0);
        if (chill == 'N')
            chillStatus = false;
        else
            chillStatus = true;

        Beverage someBeverage = null;

        if (type == 1) {
            System.out.print("Type: ");
            sc.nextLine();
            waterType = sc.nextLine();

            if (waterType == "Regular")
                someBeverage = new Water(volume, chillStatus);
            else
                someBeverage = new Water(volume, chillStatus, waterType);
        } else if (type == 2) {
            System.out.print("Alcoholic content: ");
            alc = sc.nextDouble();
            someBeverage = new Beer(volume, chillStatus, alc);
        }

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(someBeverage);

        sc.close();
    }
}