package v2;

import java.io.IOException;

/**
 * @author johnny@waterballsa.tw
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ChatRoomServer server = new WaterballChatRoomServer(8787);
        server.startServer();
    }
}
