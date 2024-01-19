package v3;

import tunnel.Tunnel;

import java.io.IOException;
import java.net.Socket;

/**
 * @author johnny@waterballsa.tw
 */
public class Main {
    public static void main(String[] args) {
        ChatRoomServer server = new ChatRoomServer(8787,
                new SuperTunnelFactory(),
                new SuperDatabaseFactory());
    }
}

class SuperTunnel implements Tunnel {

    @Override
    public String message() throws IOException {
        return null;
    }

    @Override
    public void disconnect() throws IOException {

    }
}

class SuperTunnelFactory implements TunnelFactory {
    @Override
    public Tunnel createTunnel(Socket client) {
        return new SuperTunnel();
    }
}

class SuperDatabase implements Database { }

class SuperDatabaseFactory implements DatabaseFactory {
    @Override
    public Database createDatabase() {
        return new SuperDatabase();
    }
}
