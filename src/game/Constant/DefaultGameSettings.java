package game.Constant;

import game.data.GetConfigProperties;

import static java.lang.Integer.parseInt;

public class DefaultGameSettings {
    private static final String fileName = "defaultGameSettings";

    public static final int WIDTH = parseInt(GetConfigProperties.getValue(fileName, "width"));
    public static final int HEIGHT = parseInt(GetConfigProperties.getValue(fileName, "height"));
    public static final int LIFES = parseInt(GetConfigProperties.getValue(fileName, "lifes"));
    public static final int NUMBEROFLEVELS = parseInt(GetConfigProperties.getValue(fileName, "numberOfLevels"));
    public static final float FUEL = Float.parseFloat(GetConfigProperties.getValue(fileName, "fuelLevel"));
    public static final int S_POINTS = Integer.parseInt(GetConfigProperties.getValue(fileName, "S"));

    private DefaultGameSettings() {
        throw new AssertionError();
    }
}
