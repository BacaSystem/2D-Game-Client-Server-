package game.serverConnection;

import javax.swing.*;
import java.net.Socket;

/**
 * Klasa przechowująca informację o statusie połączenia z serwerem
 */
public class ServerStatus {
    /**
     * FLaga prechpwująca informację o statusie połączenia z serwerem
     */
    private static boolean IS_CONNECTED = false;

    /**
     * Metoda awarjna. Powinna zosiać wywołana tylko w momencie niespodziewanego utracenia połączenia z serwerem.
     * Próbuje zamknąć połączenie ze strony klienta (jeśli takowe jest jeszcze otwarte)
     * Wywołuje komunikat o niespodziwanej utracie połączenia i daje użytkownikowi możliwość używanie programu dalej
     * w trybie offline, lub zamknięcie programu.
     * @param serverSocket
     */
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

    /**
     * Ustawa flagę połączenia na true, jeśli serwerSocket nie jest pusty
     * @param socket Socket serwera (może przyjmować wartość null, wtedy flaga nie zmienia statusu).
     */
    public static void connect(Socket socket) {
        if(socket!= null) {
            if(socket.isConnected()) {
                IS_CONNECTED = true;
            }
        }
    }

    /**
     * Zwraca aktualny status flagi połączenia.
     * @return Zwraca stan flagi IS_CONNECTED
     */
    public static boolean isConnected() {
        return IS_CONNECTED;
    }
}
