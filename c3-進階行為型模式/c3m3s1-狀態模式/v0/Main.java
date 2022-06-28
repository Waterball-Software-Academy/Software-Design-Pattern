package v0;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Main {
    public static void main(String[] args) {
        TicketSystem ticketSystem = new TicketSystem(2);

        System.out.println("【Case 1】 Enough coins -> Error 不再接受新硬幣");
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();

        System.out.println("\n【Case 2】 3 Coins + Refund -> 吐 3 枚、熄燈");
        ticketSystem.pressRefundButton();

        System.out.println("\n【Case 3】 3 coins + Buy -> 將硬幣總數歸零、出一張票、熄燈");
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();

        System.out.println("\n【Case 4】 0 coins + Buy -> Error 金幣不足");
        ticketSystem.pressBuyButton();

        System.out.println("\n【Case 5】 0 Coins + Refund -> 吐 0 枚");
        ticketSystem.pressRefundButton();

        System.out.println("\n【Case 6】 0 tickets + Insert Coin -> 立即吐出 1 枚硬幣");
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
        ticketSystem.pressBuyButton();
        ticketSystem.insertCoin();
        ticketSystem.insertCoin();
    }
}
