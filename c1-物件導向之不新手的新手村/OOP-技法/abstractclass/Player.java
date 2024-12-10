package abstractclass;

/**
 * @author johnny@waterballsa.tw
 */
public abstract class Player {
    private final int number;

    public Player(int number) {
        this.number = number;
    }

    public abstract Decision decide();

    public int getNumber() {
        return number;
    }
}
