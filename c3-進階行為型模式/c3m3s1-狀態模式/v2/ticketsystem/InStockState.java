package v2.ticketsystem;

import static v2.ticketsystem.TicketSystem.PRICE;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class InStockState extends State {

    public InStockState(TicketSystem ticketSystem) {
        this(ticketSystem, ticketSystem.getTickets());
    }

    public InStockState(TicketSystem ticketSystem, int ticketsInStock) {
        super(ticketSystem);
        ticketSystem.setTicketsInStock(ticketsInStock);
        ticketSystem.turnLight(false);
    }

    @Override
    public void insertCoin() {
        ticketSystem.setTotal(ticketSystem.getTotal() + 1);
        if (ticketSystem.getTotal() < PRICE) {
            ticketSystem.updateMoneyDisplay();
        } else {
            ticketSystem.setState(new EnoughCoinsState(ticketSystem));
            ticketSystem.turnLight(true);
        }
    }

    @Override
    public void fillInTickets(int tickets) {
        ticketSystem.setTicketsInStock(ticketSystem.getTickets() + tickets);
    }

    @Override
    public void pressBuyButton() {
        System.out.println("[ERROR] You haven't inserted enough coins.");
    }

    @Override
    public void pressRefundButton() {
        ticketSystem.spitCoins(ticketSystem.getTotal());
    }
}
