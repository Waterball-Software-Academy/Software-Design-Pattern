package v2.ticketsystem;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public abstract class State {
    protected TicketSystem ticketSystem;
    public abstract void insertCoin();
    public abstract void fillInTickets(int tickets);
    public abstract void pressBuyButton();
    public abstract void pressRefundButton();

    public State(TicketSystem ticketSystem) {
        this.ticketSystem = ticketSystem;
    }
}
