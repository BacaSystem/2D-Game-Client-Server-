package game.Constant;

import game.data.GetConfigProperties;

public final class MenuWindowStates {
    private static final String fileName = "menu";

    public static final String MENU = "MainMenu";
    public static final String HELP = "Help";
    public static final String NEW_GAME = "NewGame";
    public static final String HIGH_SCORES = "HighScores";
    public static final String EXIT = "Exit";

    public static final String MENU_BUTTON = GetConfigProperties.getValue(fileName, "backToMain");
    public static final String HELP_BUTTON = GetConfigProperties.getValue(fileName, "help");
    public static final String NEW_GAME_BUTTON = GetConfigProperties.getValue(fileName, "newGame");
    public static final String HIGH_SCORES_BUTTON = GetConfigProperties.getValue(fileName, "highScores");
    public static final String EXIT_BUTTON = GetConfigProperties.getValue(fileName, "exit");

    public static final int WIDTH = Integer.parseInt(GetConfigProperties.getValue(fileName, "width"));
    public static final int HEIGHT = Integer.parseInt(GetConfigProperties.getValue(fileName, "height"));

    private MenuWindowStates() {
        throw new AssertionError();
    }
}
