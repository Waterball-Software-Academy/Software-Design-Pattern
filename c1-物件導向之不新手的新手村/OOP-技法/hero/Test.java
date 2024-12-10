package hero;

/**
 * @author johnny@waterballsa.tw
 */
public class Test {

    public static void main(String[] args) {
        Hero hero = new Hero();
        LevelSheet levelSheet = new LevelSheet();

        hero.gainExp(0, levelSheet);
        hero.gainExp(100, levelSheet);
        hero.gainExp(900, levelSheet);

        hero.gainExp(-200, levelSheet);
    }
}
