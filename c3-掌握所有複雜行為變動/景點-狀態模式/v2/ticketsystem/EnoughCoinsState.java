package v2.ticketsystem;

import static v2.ticketsystem.TicketSystem.PRICE;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class EnoughCoinsState extends State {
    public EnoughCoinsState(TicketSystem ticketSystem) {
        super(ticketSystem);
        ticketSystem.setTotal(PRICE);
        ticketSystem.turnLight(true);
    }

    @Override
    public void insertCoin() {
        System.out.println("[ERROR] You can't insert coins after the number of coins is enough.");
    }

    @Override
    public void fillInTickets(int tickets) {
        ticketSystem.setTicketsInStock(ticketSystem.getTickets() + tickets);
    }

    @Override
    public void pressBuyButton() {
        ticketSystem.issueOneTicket();
        ticketSystem.setTotal(ticketSystem.getTotal() - PRICE);

        int ticketsInStock = ticketSystem.getTickets() - 1;
        if (ticketsInStock <= 0) {
            exit(new SoldOutState(ticketSystem));
        } else {
            exit(new InStockState(ticketSystem, ticketsInStock));
        }
    }

    @Override
    public void pressRefundButton() {
        ticketSystem.spitCoins(ticketSystem.getTotal());
        ticketSystem.setState(new InStockState(ticketSystem));
    }

    private void exit(State state) {
        ticketSystem.turnLight(false);
        ticketSystem.setState(state);
    }
}
