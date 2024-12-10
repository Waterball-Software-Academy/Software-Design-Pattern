package v2;

/**
 * @author johnny@waterballsa.tw
 */
public interface Portal {

    int id();
    void access(Player player);

    Portable getTarget(Player player);

    void destroy();
}
