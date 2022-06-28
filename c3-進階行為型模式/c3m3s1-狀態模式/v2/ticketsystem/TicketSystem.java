package v2.ticketsystem;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TicketSystem {
    public static final int PRICE = 3;
    private State state;
    private int totalCoins;
    private int tickets;
    private boolean lightOn = false;

    public TicketSystem(int ticketsInStock) {
        this.tickets = ticketsInStock;
        this.state = ticketsInStock == 0 ? new SoldOutState(this) : new InStockState(this, ticketsInStock);
    }

    void setTotal(int coins) {
        this.totalCoins = coins;
    }

    public int getTotal() {
        return totalCoins;
    }

    public void insertCoin() {
        System.out.println("[ACTION] insert a coin.");
        state.insertCoin();
    }

    void turnLight(boolean on) {
        this.lightOn = on;
        System.out.printf("The light is %s.\n", on ? "on" : "off");
    }

    void updateMoneyDisplay() {
        System.out.printf("Total Coins: %d.\n", totalCoins);
    }


    public void fillInTickets(int tickets) {
        System.out.printf("[ACTION] Fill in tickets : %d.\n", tickets);
        state.fillInTickets(tickets);
    }

    public int getTickets() {
        return tickets;
    }

    void setTicketsInStock(int tickets) {
        this.tickets = tickets;
    }

    public void pressBuyButton() {
        System.out.println("[ACTION] Press the buy button.");
        state.pressBuyButton();
    }

    void issueOneTicket() {
        System.out.println("You have bought one ticket.");
    }


    public void pressRefundButton() {
        System.out.println("[ACTION] Press the refund button.");
        state.pressRefundButton();
    }

    void spitCoins(int coins) {
        totalCoins -= coins;
        System.out.printf("Spitting the coins: %d.\n", coins);
    }

    void setState(State state) {
        this.state = state;
        System.out.printf("State updated: %s.\n", state);
    }
}
