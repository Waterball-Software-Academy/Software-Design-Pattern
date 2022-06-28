package commons;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Fan {
    private int level; // {0, 1, 2, 3}

    public int getLevel() {
        return level;
    }

    public void nextLevel() {
        level = (level + 1) % 4;
        System.out.println("【 Fan 】Level -> " + level);
    }

    public void previousLevel() {
        level = level - 1 == -1 ? 3 : level - 1;
        System.out.println("【 Fan 】Level -> " + level);
    }
}
