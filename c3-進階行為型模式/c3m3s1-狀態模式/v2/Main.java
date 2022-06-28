package v2;

import v2.ticketsystem.TicketSystem;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem(2);
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressRefundButton();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.pressBuyButton();
        ticketSystem.pressBuyButton();
        ticketSystem.pressRefundButton();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.fillInTickets(5);
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.insertCoin();
    }
}
