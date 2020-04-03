package game.Constant;

import game.data.GetConfigProperties;

public class GraphicsConstants {
    private static final String fileName = "gameGraphics";

    public static final String SHIP_IMAGE = GetConfigProperties.getValue(fileName, "ship");
    public static final String FIRE_UP_IMAGE = GetConfigProperties.getValue(fileName, "fireUp");
    public static final String FIRE_DOWN_IMAGE = GetConfigProperties.getValue(fileName, "fireDown");
    public static final String FIRE_LEFT_IMAGE = GetConfigProperties.getValue(fileName, "fireLeft");
    public static final String FIRE_RIGHT_IMAGE = GetConfigProperties.getValue(fileName, "fireRight");
    public static final String GAME_OVER_IMAGE = GetConfigProperties.getValue(fileName, "gameOver");
    public static final String MENU_TEXT_IMAGE = GetConfigProperties.getValue(fileName, "menuText");
    public static final String YOU_WON_IMAGE = GetConfigProperties.getValue(fileName, "wonText");
    public static final String LANDED_IMAGE = GetConfigProperties.getValue(fileName, "landed");
    public static final String CRASHED_IMAGE = GetConfigProperties.getValue(fileName, "crashed");
    public static final String SHIP_DESTROYED_IMAGE = GetConfigProperties.getValue(fileName, "destroyed");
    public static final String PAUSE_IMAGE = GetConfigProperties.getValue(fileName, "paused");

    private GraphicsConstants() {
        throw new AssertionError();
    }
}
