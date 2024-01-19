package v3;

/**
 * @author johnny@waterballsa.tw
 */
public interface Portal {

    int id();
    void access(Player player);

    Portable getTarget(Player player);

    void destroy();
}
