package tunnel;

import tunnel.Tunnel;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;
import java.util.HashSet;

import static java.lang.String.format;
import static java.lang.System.arraycopy;

/**
 * @author johnny@waterballsa.tw
 */
public class WaterballTunnel implements Tunnel {
    public static final String WATERBALL = "Waterball!!";
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
        String username = acceptMessage();
        String message = acceptMessage();
        disconnect();
        return format("%s: %s", username, message);
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
