package v2;

import tunnel.Tunnel;
import tunnel.WaterballTunnel;

import java.net.Socket;

/**
 * @author johnny@waterballsa.tw
 */
public class WaterballChatRoomServer extends ChatRoomServer {
    public WaterballChatRoomServer(int port) {
        super(port);
    }

    @Override
    protected Tunnel createTunnel(Socket client) {
        return new WaterballTunnel(client);
    }
}
