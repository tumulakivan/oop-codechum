import java.util.*;

public class Chess {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Is white (Y - yes, N - no): ");
        boolean isWhite = sc.next().charAt(0) == 'Y';
        Pawn pawn = new Pawn(isWhite);

        sc.nextLine();

        System.out.print("Is two moves (Y - yes, N - no): ");
        boolean isTwoMoves = sc.next().charAt(0) == 'Y';

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(pawn, isTwoMoves);

        sc.close();
    }
}