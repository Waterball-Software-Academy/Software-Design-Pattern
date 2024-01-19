package v3;

import tunnel.Tunnel;
import tunnel.WaterballTunnel;

import java.net.Socket;

/**
 * @author johnny@waterballsa.tw
 */
public class WaterballTunnelFactory implements TunnelFactory {
    @Override
    public Tunnel createTunnel(Socket client) {
        return new WaterballTunnel(client);
    }
}
