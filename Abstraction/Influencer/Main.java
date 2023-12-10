import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hey there, start typing your Java code here...
        Scanner sc = new Scanner(System.in);

        System.out.print("Select Influencer (1 - Facebook, 2 - Tiktok): ");
        int type = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter name of influencer: ");
        String name = sc.nextLine();
        Influencer influencer = null;

        switch(type) {
            case 1:
                influencer = new FacebookInfluencer(name);
                break;
            case 2:
                influencer = new TiktokInfluencer(name);
                break;
            default:
                System.out.println("Unknown type");
                break;
        }

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(influencer);
    }
}