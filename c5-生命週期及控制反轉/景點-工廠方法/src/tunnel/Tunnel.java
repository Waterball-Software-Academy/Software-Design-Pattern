package tunnel;

import java.io.IOException;

/**
 * @author johnny@waterballsa.tw
 */
public interface Tunnel {
    String message() throws IOException;

    void disconnect() throws IOException;
}
