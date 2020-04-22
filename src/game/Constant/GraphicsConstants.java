package game.Constant;

import game.configReader.ConfigReader;
import game.configReader.ServerReader;

import java.net.Socket;
import java.util.Map;

/**
 * Klasa przechoowywująca ścieżki do elementów graficznych gry
 */
public class GraphicsConstants {
    private static final String serverCommand = "GET_GRAPHICS";
    /** string prechowywujący nazwę pliku ze ścieżkami do elementów graficznych gry. BEZ ROZSZERZENIA */
    private static final String fileName = "gameGraphics";

    /** ścieżka do obrazka statku */
    public static String SHIP_IMAGE;
    /** ścieżka do obrazku ognia w góre */
    public static String FIRE_UP_IMAGE;
    /** ścieżka do ognia w dół */
    public static String FIRE_DOWN_IMAGE;
    /** ścieżka do ognia w lewo */
    public static String FIRE_LEFT_IMAGE;
    /** ścieżka do ognia w prawo */
    public static String FIRE_RIGHT_IMAGE;
    /** ścieżka do obrazka koniec gry */
    public static String GAME_OVER_IMAGE;
    /** ścieżka do obrazka wciśnij enter */
    public static String MENU_TEXT_IMAGE;
    /** ścieżka do obrazka Wygrałeś */
    public static String YOU_WON_IMAGE;
    /** ścieżka do obrazka wylądowałeś */
    public static String LANDED_IMAGE;
    /** ścieżka do obrazka rozbiłeś się */
    public static String CRASHED_IMAGE;
    /** ścieżka do obrazka statek został zniszczony */
    public static String SHIP_DESTROYED_IMAGE;
    /** ścieżka do obrazka pausa */
    public static String PAUSE_IMAGE;
    /** ścieżka do obrazka meteorytu */
    public static String METEOR_IMAGE;

    private GraphicsConstants() {
        throw new AssertionError();
    }

    public static void donwloanGraphics(Socket serverSocket) {
        if(ServerStatus.isConnected()) {
            try {
                Map<String, String> data = ServerReader.getDecodedDataInMap(serverSocket, serverCommand);
                SHIP_IMAGE = data.get("ship");
                FIRE_UP_IMAGE = data.get("fireUp");
                FIRE_DOWN_IMAGE = data.get("fireDown");
                FIRE_LEFT_IMAGE = data.get("fireLeft");
                FIRE_RIGHT_IMAGE = data.get("fireRight");
                GAME_OVER_IMAGE = data.get("gameOver");
                MENU_TEXT_IMAGE = data.get("menuText");
                YOU_WON_IMAGE = data.get("wonText");
                LANDED_IMAGE = data.get("landed");
                CRASHED_IMAGE = data.get("crashed");
                SHIP_DESTROYED_IMAGE = data.get("destroyed");
                PAUSE_IMAGE = data.get("paused");
                METEOR_IMAGE = data.get("meteor");
                System.out.println("Graphics from server");
            } catch (Exception e) {
                ServerStatus.connectionLost();
            }
        }
        if(!ServerStatus.isConnected()) {
            System.out.println("Offline graphics");
            SHIP_IMAGE = ConfigReader.getValue(fileName, "ship");
            FIRE_UP_IMAGE = ConfigReader.getValue(fileName, "fireUp");
            FIRE_DOWN_IMAGE = ConfigReader.getValue(fileName, "fireDown");
            FIRE_LEFT_IMAGE = ConfigReader.getValue(fileName, "fireLeft");
            FIRE_RIGHT_IMAGE = ConfigReader.getValue(fileName, "fireRight");
            GAME_OVER_IMAGE = ConfigReader.getValue(fileName, "gameOver");
            MENU_TEXT_IMAGE = ConfigReader.getValue(fileName, "menuText");
            YOU_WON_IMAGE = ConfigReader.getValue(fileName, "wonText");
            LANDED_IMAGE = ConfigReader.getValue(fileName, "landed");
            CRASHED_IMAGE = ConfigReader.getValue(fileName, "crashed");
            SHIP_DESTROYED_IMAGE = ConfigReader.getValue(fileName, "destroyed");
            PAUSE_IMAGE = ConfigReader.getValue(fileName, "paused");
            METEOR_IMAGE = ConfigReader.getValue(fileName, "meteor");
        }
    }
}
