package tw.waterballsa.designpattern.c1m2s1;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class ContentScene extends Scene {
    public ContentScene(String name, int number, int expAward) {
        super(name, number, expAward);
    }

    @Override
    public int calculateExpAward() {
        // ContentScene 特有的經驗值算法
        return (int) (expAward * 1.1);
    }
}
