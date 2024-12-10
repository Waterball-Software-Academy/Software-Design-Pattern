package v2;

import tunnel.Tunnel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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

            Tunnel tunnel = createTunnel(client);
            String message = tunnel.message();

            System.out.println(message);
        }
    }

    protected abstract Tunnel createTunnel(Socket client);

}
