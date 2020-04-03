package game.Constant;

import game.data.GetConfigProperties;

/**
 * Klasa przechoowywująca ścieżki do elementów graficznych gry
 */
public class GraphicsConstants {
    /** string prechowywujący nazwę pliku ze ścieżkami do elementów graficznych gry. BEZ ROZSZERZENIA */
    private static final String fileName = "gameGraphics";

    /** ścieżka do obrazka statku */
    public static final String SHIP_IMAGE = GetConfigProperties.getValue(fileName, "ship");
    /** ścieżka do obrazku ognia w góre */
    public static final String FIRE_UP_IMAGE = GetConfigProperties.getValue(fileName, "fireUp");
    /** ścieżka do ognia w dół */
    public static final String FIRE_DOWN_IMAGE = GetConfigProperties.getValue(fileName, "fireDown");
    /** ścieżka do ognia w lewo */
    public static final String FIRE_LEFT_IMAGE = GetConfigProperties.getValue(fileName, "fireLeft");
    /** ścieżka do ognia w prawo */
    public static final String FIRE_RIGHT_IMAGE = GetConfigProperties.getValue(fileName, "fireRight");
    /** ścieżka do obrazka koniec gry */
    public static final String GAME_OVER_IMAGE = GetConfigProperties.getValue(fileName, "gameOver");
    /** ścieżka do obrazka wciśnij enter */
    public static final String MENU_TEXT_IMAGE = GetConfigProperties.getValue(fileName, "menuText");
    /** ścieżka do obrazka Wygrałeś */
    public static final String YOU_WON_IMAGE = GetConfigProperties.getValue(fileName, "wonText");
    /** ścieżka do obrazka wylądowałeś */
    public static final String LANDED_IMAGE = GetConfigProperties.getValue(fileName, "landed");
    /** ścieżka do obrazka rozbiłeś się */
    public static final String CRASHED_IMAGE = GetConfigProperties.getValue(fileName, "crashed");
    /** ścieżka do obrazka statek został zniszczony */
    public static final String SHIP_DESTROYED_IMAGE = GetConfigProperties.getValue(fileName, "destroyed");
    /** ścieżka do obrazka pausa */
    public static final String PAUSE_IMAGE = GetConfigProperties.getValue(fileName, "paused");

    private GraphicsConstants() {
        throw new AssertionError();
    }
}
