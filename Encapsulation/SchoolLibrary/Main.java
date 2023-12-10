import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Hey there, start typing your Java code here...
        Scanner sc = new Scanner(System.in);
        Book book = new Book();
        String title;
        int numberOfPages;
        char fict;
        boolean isFictional;

        System.out.print("Enter book title: ");
        title = sc.nextLine();
        System.out.print("Enter book number of pages: ");
        numberOfPages = sc.nextInt();
        System.out.print("Is fictional (Y - yes, N - no): ");
        fict = sc.next().charAt(0);

        fict = Character.toUpperCase(fict);
        if (fict == 'Y') {
            isFictional = true;
            book.setIsFictional(isFictional);
        } else {
            isFictional = false;
            book.setIsFictional(isFictional);
        }

        book.setTitle(title);
        book.setNumberOfPages(numberOfPages);
        book.setIsFictional(isFictional);

        System.out.println("title: " + book.getTitle());
        System.out.println("number of pages: " + book.getNumberOfPages());
        boolean isF = book.getIsFictional();
        if (isF)
            System.out.println("is fictional: true");
        else
            System.out.println("is fictional: false");

        // NOTE: Uncomment the line below when you want to submit your solution already
        Tester.test(book);

        sc.close();
    }
}