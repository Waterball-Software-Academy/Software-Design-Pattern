package v1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.String.format;

/**
 * @author johnny@waterballsa.tw
 */

public abstract class ChatRoomServer {
    private final int port;

    public ChatRoomServer(int port) {
        this.port = port;
    }

    public void startServer() throws IOException {
        try (var server = new ServerSocket(port)) {
            Socket client = server.accept();

            // Tunnel tunnel = 某種 Tunnel (client);
            // String message = tunnel.message();

            // System.out.println(message);
        }
    }
}
