package v3;

import v1.ExpenseTrackingCLI;
import v1.SuperExpenseTrackingSystem;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        ExpenseTrackingCLI cli = new ExpenseTrackingCLI(new TrialVersionSuperExpenseTrackingSystemProxy());
        cli.start();
    }
}
