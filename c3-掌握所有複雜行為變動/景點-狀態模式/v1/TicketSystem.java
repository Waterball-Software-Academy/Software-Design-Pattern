package v1;

import static v1.TicketSystem.State.*;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class TicketSystem {
    private static final int PRICE = 3;
    private State state;
    private int total;
    private int tickets;
    private boolean lightOn = false;

    public TicketSystem(int tickets) {
        this.tickets = tickets;
        this.state = tickets == 0 ? SOLD_OUT : IN_STOCK;
    }

    public enum State {
        IN_STOCK, SOLD_OUT, ENOUGH_COINS
    }

    public void insertCoin() {
        System.out.println("[ACTION] insert a coin.");
        if (state == ENOUGH_COINS) {
            System.out.println("[ERROR] You can't insert coins after the number of coins is enough.");
        } else {
            total++;
            if (state == IN_STOCK) {
                updateCoinsDisplay();
                if (total == PRICE) {
                    setState(ENOUGH_COINS);
                }
            } else {
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
        if (state == SOLD_OUT) {
            setState(IN_STOCK);
        }
    }

    public void pressBuyButton() {
        System.out.println("[ACTION] Press the buy button.");
        if (state == ENOUGH_COINS) {
            total -= PRICE;
            updateCoinsDisplay();
            issueOneTicket();
            if (tickets <= 0) {
                setState(SOLD_OUT);
            } else if (total < PRICE) {
                setState(IN_STOCK);
            }
        } else {
            System.out.println("[ERROR] You haven't inserted enough coins.");
        }
    }

    private void issueOneTicket() {
        tickets --;
        System.out.println("You have bought one ticket.");
    }

    public void pressRefundButton() {
        System.out.println("[ACTION] Press the refund button.");
        spitCoins(total);
        if (state == ENOUGH_COINS) {
            setState(IN_STOCK);
        }
    }

    private void spitCoins(int coins) {
        total -= coins;
        System.out.printf("Spitting the coins: %d.\n", coins);
    }

    public void setState(State state) {
        if (this.state == ENOUGH_COINS) {
            turnLight(false); // exit
        }
        this.state = state;
        System.out.printf("State updated: %s.\n", state);
        if (state == ENOUGH_COINS) {
            total = PRICE;
            turnLight(true);
        }
        if (state == IN_STOCK && total > PRICE) {
            spitCoins(PRICE - total - 1); // 吐掉多餘的硬幣
        }
        if ((state == ENOUGH_COINS || state == IN_STOCK) && tickets <= 0) {
            throw new IllegalStateException("Cannot set to the IN_STOCK state if tickets <= 0.");
        }
    }
}
