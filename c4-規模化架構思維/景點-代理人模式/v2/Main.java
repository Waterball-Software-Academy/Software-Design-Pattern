package v2;

import v1.ExpenseTrackingCLI;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        ExpenseTrackingCLI cli = new ExpenseTrackingCLI(new VirtualSuperExpenseTrackingSystemProxy());
        cli.start();
    }
}
