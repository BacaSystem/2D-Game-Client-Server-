package game.Constant;

import game.data.GetConfigProperties;

public class LoadLevel {
    public static float GRAVITY_SPEED;

    public static void getLevel(int Level) {
        String fileName;
        switch (Level) {
            case 2:
                fileName = "level2";
                break;
            case 3:
                fileName = "level3";
                break;
            case 4:
                fileName = "level4";
                break;
            default:
                fileName = "level1";
                break;
        }

        GRAVITY_SPEED = Float.parseFloat(GetConfigProperties.getValue(fileName, "gravitySpeed"));
    }
    private LoadLevel() {
        throw new AssertionError();
    }

}
