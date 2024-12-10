package v1;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        ExpenseTrackingCLI cli = new ExpenseTrackingCLI(new SuperExpenseTrackingSystem());
        cli.start();
    }
}
