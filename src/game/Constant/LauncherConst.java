package game.Constant;

import game.data.GetConfigProperties;

/**
 * Klasa przechoowywująca stałe Launchera gry
 */
public class LauncherConst {
    /** string prechowywujący nazwę pliku ze ścieżkami do stałych Launchera gry. BEZ ROZSZERZENIA */
    private static final String fileName = "launcher";

    /** stała przechowywująca tytuł okna */
    public static final String LAUNCHER_TITLE = GetConfigProperties.getValue(fileName, "launcherTitle");
    /** stała przechowywująca label pola tekstowego nick */
    public static final String NICK_LABEL = GetConfigProperties.getValue(fileName, "nickLabel");
    /** stała przechowywująca label guzika gry online */
    public static final String ONLINE_LABEL = GetConfigProperties.getValue(fileName, "onlineLabel");
    /** stała przechowywująca label guzika gry offline */
    public static final String OFFLINE_LABEL = GetConfigProperties.getValue(fileName, "offlineLabel");
    /** stała przechowywująca label pola tekstowego IP */
    public static final String IP_LABEL = GetConfigProperties.getValue(fileName, "ipLabel");
    /** stała przechowywująca label pola tekstowego portu */
    public static final String PORT_LABEL = GetConfigProperties.getValue(fileName, "portLabel");
    /** stała przechowywująca domyślna szerokość okna gry */
    public static final int WIDTH = Integer.parseInt(GetConfigProperties.getValue(fileName, "width"));
    /** stała przechowywująca domyślna wysokość okna gry */
    public static final int HEIGHT = Integer.parseInt(GetConfigProperties.getValue(fileName, "height"));

    private LauncherConst() {
        throw new AssertionError();
    }
}
