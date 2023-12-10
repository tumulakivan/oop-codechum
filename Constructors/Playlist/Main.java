import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter constructor type: ");
        int constructorType = sc.nextInt();

        Music music = null;
        int duration = 0;
        char durationType = '0';

        if (constructorType == 1) {
            System.out.print("Enter music duration: ");
            duration = sc.nextInt();
            System.out.print("Enter music genre: ");
            String genre = sc.nextLine();
            music = new Music(duration, genre);
        } else if (constructorType == 2) {
            System.out.print("Enter music duration: ");
            duration = sc.nextInt();
            System.out.print("Enter music genre: ");
            String genre = sc.nextLine();
            System.out.print("Enter music duration type: ");
            durationType = sc.next().charAt(0);
            music = new Music(duration, genre, durationType);
        }

        if (constructorType == 1)
            Tester.test(music, duration, 'm', constructorType);
        else
            Tester.test(music, duration, durationType, constructorType);

        sc.close();
    }
}