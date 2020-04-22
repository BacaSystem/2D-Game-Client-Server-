package game.Constant;

import javax.swing.*;
import java.net.Socket;

public class ServerStatus {
    private static boolean IS_CONNECTED = false;

    public static void connectionLost() {
        IS_CONNECTED = false;
        JOptionPane.showMessageDialog(null, "We've lost connection with server. You're playing offline now.", "Connection issue", JOptionPane.ERROR_MESSAGE);
    }

    public static void connected(Socket socket) {
        if(socket!= null) {
            if(socket.isConnected()) {
                IS_CONNECTED = true;
            }
        }
    }

    public static boolean isConnected() {
        return IS_CONNECTED;
    }
}
