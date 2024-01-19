package v3;

import tunnel.Tunnel;

import java.net.Socket;

/**
 * @author johnny@waterballsa.tw
 */
public interface TunnelFactory {
    Tunnel createTunnel(Socket client);
}
