package v1;

import java.util.List;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public interface ExpenseTrackingSystem {
    List<Expense> getExpenses();
    void addExpense(Expense expense);
    void editExpense(Expense expense);
    void exportCSV(String filename);
}
