package abstractclass;

/**
 * @author johnny@waterballsa.tw
 */
public class AI extends Player {
    public AI(int number) {
        super(number);
    }

    @Override
    public Decision decide() {
        int randomNum = (int) (Math.random() * 3);
        switch (randomNum) {
            case 0:
                return Decision.SCISSORS;
            case 1:
                return Decision.PAPER;
            default:
                return Decision.STONE;
        }
    }
}
