package game.Constant;

import game.data.GetConfigProperties;

public class DefaultGameSettings {
    private static final String fileName = "defaultGameSettings";

    public static final int WIDTH = Integer.parseInt(GetConfigProperties.getValue(fileName, "width"));
    public static final int HEIGHT = Integer.parseInt(GetConfigProperties.getValue(fileName, "height"));
    public static final int LIFES = Integer.parseInt(GetConfigProperties.getValue(fileName, "lifes"));
    public static final Float FUEL = Float.parseFloat(GetConfigProperties.getValue(fileName, "fuelLevel"));

    private DefaultGameSettings() {
        throw new AssertionError();
    }
}
