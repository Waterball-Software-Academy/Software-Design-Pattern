package v1;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Expense {
    private final String id;
    private final String description;
    private final BigDecimal cost;
    private final LocalDateTime createdTime;

    public Expense(String description, BigDecimal cost) {
        this(UUID.randomUUID().toString(),
                description, cost, LocalDateTime.now());
    }

    public Expense(String id, String description, BigDecimal cost, LocalDateTime createdTime) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.createdTime = createdTime;
    }

    public Expense edit(String description, BigDecimal cost) {
        return new Expense(id, description, cost, createdTime);
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }
}
