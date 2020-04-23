package game.Constant;

import javax.swing.*;
import java.net.Socket;

public class ServerStatus {
    private static boolean IS_CONNECTED = false;

    public static void connectionLost(Socket serverSocket) {
        IS_CONNECTED = false;
        String text = "Server not responding, trying to close connection.";
        try {
            serverSocket.close();
            System.out.println(text + " SUCCES!");
        } catch(Exception e) {
            System.out.println(text + " FAILED - server connection already closed");
        }
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null, "We've lost connection with the server. Do you want to play offline?", "Connection issue", dialogButton);
        if(dialogResult == JOptionPane.NO_OPTION) {
            System.out.println("NO");
            System.exit(1);
        }


    }

    public static void connect(Socket socket) {
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
