package v0;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TicketSystem {
    private static final int PRICE = 3;
    private int total;
    private int tickets;
    private boolean lightOn = false;

    public TicketSystem(int tickets) {
        this.tickets = tickets;
    }

    public void insertCoin() {
        System.out.println("[ACTION] insert a coin.");
        if (total >= PRICE) /*Enough coins*/ {
            System.out.println("[ERROR] You can't insert coins after the number of coins is enough.");
        } else {
            total++;
            if (tickets > 0) /*有票存 -> 更新螢幕、如果 Enough coins 就亮燈*/ {
                updateCoinsDisplay();
                if (total == PRICE) {
                    turnLight(true);
                }
            } else /*沒票存 -> 吐硬幣*/{
                spitCoins(total);
            }
        }
    }

    private void turnLight(boolean on) {
        this.lightOn = on;
        System.out.printf("The light is %s.\n", on ? "on" : "off");
    }

    private void updateCoinsDisplay() {
        System.out.printf("Total Coins: %d.\n", total);
    }

    public void fillTickets(int tickets) {
        System.out.printf("[ACTION] Fill in tickets : %d.\n", tickets);
        this.tickets += tickets;
    }

    public void pressBuyButton() {
        System.out.println("[ACTION] Press the buy button.");
        if (lightOn) {
            total -= PRICE;
            updateCoinsDisplay();
            issueOneTicket();
            turnLight(false);
        } else {
            System.out.println("[ERROR] You haven't inserted enough coins.");
        }
    }

    private void issueOneTicket() {
        tickets--;
        System.out.println("You have bought one ticket.");
    }

    public void pressRefundButton() {
        System.out.println("[ACTION] Press the refund button.");
        spitCoins(total);
        if (lightOn) {
            turnLight(false);
        }
    }

    private void spitCoins(int coins) {
        total -= coins;
        System.out.printf("Spitting the coins: %d.\n", coins);
    }

}
