package v1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class SuperExpenseTrackingSystem implements ExpenseTrackingSystem {
    private static final String FILE_NAME = "expenses.json";
    private final List<Expense> expenses;
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.registerModule(new JavaTimeModule());
    }

    public SuperExpenseTrackingSystem() {
        try {
            expenses = readExpensesFromFile();
            System.out.printf("[%d 筆帳目] ✓\n", expenses.size());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private List<Expense> readExpensesFromFile() throws IOException {
        System.out.print("載入資料中⋯⋯");
        return MAPPER.readValue(new File(FILE_NAME),
                new TypeReference<List<ExpenseDocument>>() {
                }).stream()
                .map(ExpenseDocument::convert).collect(toList());
    }

    @Override
    public List<Expense> getExpenses() {
        return new ArrayList<>(expenses);
    }

    @Override
    public void addExpense(Expense expense) {
        expenses.add(expense);
        saveData();
    }

    @Override
    public void editExpense(Expense expense) {
        for (int i = 0; i < expenses.size(); i++) {
            if (expenses.get(i).getId().equals(expense.getId())) {
                expenses.remove(i);
                expenses.add(i, expense);
                break;
            }
        }
        saveData();
    }

    @Override
    public void exportCSV(String filename) {
        CSVFormat csvFormat = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader("id", "description", "cost", "createTime")
                .setAutoFlush(true).build();
        try (CSVPrinter printer = csvFormat
                .print(new File(filename), StandardCharsets.UTF_8)) {
            for (Expense expense : expenses) {
                printer.printRecord(expense.getId(), expense.getDescription(),
                        expense.getCost(), expense.getCreatedTime().format(DateTimeFormatter.ISO_DATE));
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    private void saveData() {
        try {
            MAPPER.writeValue(new File(FILE_NAME), expenses);
        } catch (IOException e) {
            throw new IllegalStateException("Can't save data.");
        }
    }

}

class ExpenseDocument {
    public String id, description;
    public BigDecimal cost;
    public LocalDateTime createdTime;

    Expense convert() {
        return new Expense(id, description, cost, createdTime);
    }
}