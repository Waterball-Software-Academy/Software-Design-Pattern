package v1;


import static v1.TicketSystem.State.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem(2);

        System.out.println("【Case 1】 Enough coins -> Error 不再接受新硬幣");
        ticketSystem.setState(ENOUGH_COINS);
        ticketSystem.insertCoin();

        System.out.println("\n【Case 2】 Enough Coins + Refund -> 吐 3 枚、熄燈");
        ticketSystem.pressRefundButton();

        System.out.println("\n【Case 3】 Enough coins + Buy -> 將硬幣總數歸零、出一張票、熄燈");
        ticketSystem.setState(ENOUGH_COINS);
        ticketSystem.pressBuyButton();

        System.out.println("\n【Case 4】 In-Stock(0 Coins) + Buy -> Error 金幣不足");
        ticketSystem.setState(IN_STOCK);
        ticketSystem.pressBuyButton();

        System.out.println("\n【Case 5】 In-Stock(0 Coins) + Refund -> 吐 0 枚");
        ticketSystem.pressRefundButton();

        System.out.println("\n【Case 6】 Sold-Out + Insert Coin -> 立即吐出 1 枚硬幣");
        ticketSystem.setState(SOLD_OUT);
        ticketSystem.insertCoin();
    }
}
