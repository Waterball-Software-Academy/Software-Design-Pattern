package v1;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.lang.Math.min;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Collections.reverse;
import static utils.ScannerUtils.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ExpenseTrackingCLI {
    private final ExpenseTrackingSystem system;
    public static final int EXPENSES_PAGE_SIZE = 7;

    public ExpenseTrackingCLI(ExpenseTrackingSystem system) {
        this.system = system;
    }

    public void start() {
        while (true) {
            try {
                System.out.println("選擇功能：(1) 新增一筆帳目 (2) 編輯一筆帳目 (3) 匯出 CSV：");
                int choice = inputIntegerBetween(1, 3, "請輸入數字 1~3！");
                switch (choice) {
                    case 1:
                        addExpense();
                        break;
                    case 2:
                        editExpense();
                        break;
                    case 3:
                        exportCSV();
                        break;
                }
            } catch (Exception err) {
                System.err.println(err.getMessage());
            }
        }
    }

    private void addExpense() {
        Expense expense = inputExpense();
        system.addExpense(expense);
        System.out.printf("成功新增一筆帳目 -- <%s> %.3f\n", expense.getDescription(), expense.getCost().doubleValue());
    }

    private Expense inputExpense() {
        System.out.println("請輸入這筆帳目的描述：");
        String description = inputLine();
        System.out.println("請輸入這筆帳目的支出金額：");
        BigDecimal cost = BigDecimal.valueOf(inputDoubleBetween(0, Double.MAX_VALUE, "請輸入此本帳目的支出！"));
        return new Expense(description, cost);
    }

    private void editExpense() {
        List<Expense> expenses = system.getExpenses();
        reverse(expenses);
        int editedExpenseIndex = selectEditedExpenseIndex(expenses);
        Expense edited = inputEditedExpense(expenses, editedExpenseIndex);
        system.editExpense(edited);
    }

    private int selectEditedExpenseIndex(List<Expense> expenses) {
        int page = 0;
        int editedExpenseIndex = -1;
        while (editedExpenseIndex == -1) {
            printExpenses(expenses, page);
            System.out.println("請輸入欲編輯的帳目編號（上一頁 'P'｜下一頁 'N'）：");
            String choice = inputLine();
            if ("P".equalsIgnoreCase(choice)) {
                page = Math.max(0, page - 1);
            } else if ("N".equalsIgnoreCase(choice)) {
                page++;
            } else {
                try {
                    editedExpenseIndex = parseInt(choice);
                } catch (NumberFormatException ignored) {
                    System.err.println("請輸入數字！");
                }
            }
        }
        return editedExpenseIndex;
    }

    private Expense inputEditedExpense(List<Expense> expenses, int editedExpenseIndex) {
        Expense expense = expenses.get(editedExpenseIndex);
        System.out.println("請編輯這筆帳目的描述：");
        String description = inputLine();
        System.out.println("請編輯這筆帳目的支出金額：");
        BigDecimal cost = BigDecimal.valueOf(inputDoubleBetween(0, Double.MAX_VALUE, "請輸入此本帳目的支出！"));
        return expense.edit(description, cost);
    }

    private void printExpenses(List<Expense> expenses, int page) {
        int end = min(expenses.size(), (page + 1) * EXPENSES_PAGE_SIZE);
        for (int i = page * EXPENSES_PAGE_SIZE; i < end; i++) {
            Expense expense = expenses.get(i);
            System.out.printf("[%d] %s\n", i,
                    format("%s %.3f %s", expense.getDescription(), expense.getCost(),
                            expense.getCreatedTime().format(ofPattern("yyyy/MM/dd"))));
        }
    }

    private void exportCSV() {
        System.out.println("請輸出 CSV 檔案名稱：");
        String filename = input();
        system.exportCSV(filename);
    }

    public static void main(String[] args) {
        new ExpenseTrackingCLI(new SuperExpenseTrackingSystem()).start();
    }
}
