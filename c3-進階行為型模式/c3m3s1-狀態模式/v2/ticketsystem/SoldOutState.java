package v2.ticketsystem;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class SoldOutState extends State {
    public SoldOutState(TicketSystem ticketSystem) {
        super(ticketSystem);
        ticketSystem.turnLight(false);
    }

    @Override
    public void insertCoin() {
        ticketSystem.setTotal(ticketSystem.getTotal() + 1);
        ticketSystem.spitCoins(ticketSystem.getTotal());
    }

    @Override
    public void fillInTickets(int tickets) {
        ticketSystem.setState(new InStockState(ticketSystem,
                ticketSystem.getTickets() + tickets));
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
