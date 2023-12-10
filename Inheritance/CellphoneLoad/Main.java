import java.util.*;

public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter load type (1 - CITLoad, 2 - CTLoad, 3 - ILoad): ");
        int type = sc.nextInt();

        CellphoneLoad cellphoneLoad = null;

        if(type == 1 ) {
            cellphoneLoad = new CITLoad();
        }else if(type == 2) {
            cellphoneLoad = new CTLoad();
        }else if (type == 3) {
            cellphoneLoad = new ILoad();
        }

        Phone phone = new Phone();

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(cellphoneLoad, phone);

        sc.close();
    }
}