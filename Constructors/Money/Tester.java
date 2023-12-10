public class Tester {
    public Tester() {
    }

    public static void test(Money money, int constructorType) {
        if (constructorType == 1) {
            Money newMoney = new Money();

            if (newMoney.amount == 0 && newMoney.denomination == "Unknown") {
                System.out.println("PASSED");
                System.out.println("SUCCESS");
                return;
            }
        } else if (constructorType == 2) {
            Money newMoney = new Money(money.amount);

            if (newMoney.amount == money.amount && newMoney.denomination == "Unknown") {
                System.out.println("PASSED 1");
                int testAmount = 20;

                Money testMoney = new Money(testAmount);

                if (testMoney.amount == testAmount && testMoney.denomination == "Unknown") {
                    System.out.println("PASSED 2");
                    System.out.println("SUCCESS");
                    return;
                }
            }
        } else {
            Money newMoney = new Money(money.amount, money.denomination);

            if (newMoney.amount == money.amount && newMoney.denomination == money.denomination) {
                System.out.println("PASSED 1");
                int testAmount = 20;
                String testDenomination = "TestDenomination";

                Money testMoney = new Money(testAmount, testDenomination);

                if (testMoney.amount == testAmount && testMoney.denomination == testDenomination) {
                    System.out.println("PASSED 2");
                    System.out.println("SUCCESS");
                    return;
                }
            }
        }

        System.out.println("FAILED");
    }
}