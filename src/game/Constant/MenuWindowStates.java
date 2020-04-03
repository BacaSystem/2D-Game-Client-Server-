package game.Constant;

import game.data.GetConfigProperties;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

/**
 * Klasa przechoowywująca stałe menu gry
 */
public final class MenuWindowStates {
    /** string prechowywujący nazwę pliku ze ścieżkami do stałych menu gry. BEZ ROZSZERZENIA */
    private static final String fileName = "menu";

    /** stała przechowywująca nazwę akcji przycisku menu */
    public static final String MENU = "MainMenu";
    /** stała przechowywująca nazwę akcji przycisku help */
    public static final String HELP = "Help";
    /** stała przechowywująca nazwę akcji przycisku nowej gry */
    public static final String NEW_GAME = "NewGame";
    /** stała przechowywująca nazwę akcji przycisku tablicy najlepszych wyników */
    public static final String HIGH_SCORES = "HighScores";
    /** stała przechowywująca nazwę akcji wyjdź */
    public static final String EXIT = "Exit";

    /** stała przechowywująca nazwę przycisku menu */
    public static final String MENU_BUTTON = GetConfigProperties.getValue(fileName, "backToMain");
    /** stała przechowywująca nazwę przycisku help */
    public static final String HELP_BUTTON = GetConfigProperties.getValue(fileName, "help");
    /** stała przechowywująca nazwę przycisku nowej gry */
    public static final String NEW_GAME_BUTTON = GetConfigProperties.getValue(fileName, "newGame");
    /** stała przechowywująca nazwę przycisku tablicy najlepszych wyników */
    public static final String HIGH_SCORES_BUTTON = GetConfigProperties.getValue(fileName, "highScores");
    /** stała przechowywująca nazwę wyjdź */
    public static final String EXIT_BUTTON = GetConfigProperties.getValue(fileName, "exit");

    /** stała przechowywująca domyślna szerokość okna gry */
    public static final int WIDTH = parseInt(GetConfigProperties.getValue(fileName, "width"));
    /** stała przechowywująca domyślna wysokość okna gry */
    public static final int HEIGHT = parseInt(GetConfigProperties.getValue(fileName, "height"));

    /** stała przechowywująca domyślna szerokość przycisku  */
    public static final int BUTTON_WIDTH = parseInt(GetConfigProperties.getValue(fileName, "buttonWidth"));
    /** stała przechowywująca domyślna wysokość przycisku  */
    public static final int BUTTON_HEIGHT = parseInt(GetConfigProperties.getValue(fileName, "buttonHeight"));

    private MenuWindowStates() {
        throw new AssertionError();
    }
}
