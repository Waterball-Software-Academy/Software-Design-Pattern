package v3;

import tunnel.Tunnel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * V3: 組合取代繼承
 * @author johnny@waterballsa.tw
 */
public class ChatRoomServer {
    private final int port;
    private final TunnelFactory tunnelFactory;
    private final DatabaseFactory databaseFactory;

    public ChatRoomServer(int port,
                          TunnelFactory tunnelFactory,
                          DatabaseFactory databaseFactory) {
        this.port = port;
        this.tunnelFactory = tunnelFactory;
        this.databaseFactory = databaseFactory;
    }

    public void startServer() throws IOException {
        try (var server = new ServerSocket(port)) {
            Socket client = server.accept();

            Tunnel tunnel = tunnelFactory.createTunnel(client);
//            Database database = databaseFactory.createDatabase();
            createDatabase
            String message = tunnel.message();

            System.out.println(message);
        }
    }

}
