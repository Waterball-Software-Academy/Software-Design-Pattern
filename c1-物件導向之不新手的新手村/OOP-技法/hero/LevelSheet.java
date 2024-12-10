package hero;

/**
 * @author johnny@waterballsa.tw
 */
public class LevelSheet {
    public int queryLevel(int totalExp) {
        return totalExp / 1000 + 1;
    }
}
