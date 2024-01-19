package tunnel;

import tunnel.Tunnel;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @author johnny@waterballsa.tw
 */
public class WaterballTunnel implements Tunnel {
    public static final String WATERBALL = "水球好棒!!";
    private final InputStream in;
    private final Socket client;

    public WaterballTunnel(Socket client) {
        try {
            in = client.getInputStream();
            this.client = client;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String message() throws IOException {
        String message = acceptMessage();
        disconnect();
        return message;
    }

    @Override
    public void disconnect() throws IOException {
        if (!client.isClosed()) {
            client.close();
        }
    }

    private String acceptMessage() throws IOException {
        byte[] messagePacket = new byte[1024];
        int length = in.read(messagePacket);
        if (length == -1 ||
                !WATERBALL.equals(new String(messagePacket, 0, WATERBALL.length()))) {
            throw new IllegalStateException("invalid data");
        }

        return new String(messagePacket, WATERBALL.length(), length - WATERBALL.length());
    }
}
