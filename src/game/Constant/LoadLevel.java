package game.Constant;

import game.data.GetConfigProperties;

import java.util.Arrays;

public class LoadLevel {
    public static float GRAVITY_SPEED;
    public static int xStart;
    public static int yStart;
    public static int[] xVerticies;
    public static int[] yVerticies;
    public static int[] xLanding;
    public static int[] yLanding;

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
            case 1:
            default:
                fileName = "level1";
                break;
        }

        GRAVITY_SPEED = Float.parseFloat(GetConfigProperties.getValue(fileName, "gravitySpeed"));
        xStart = Integer.parseInt(GetConfigProperties.getValue(fileName, "xStart"));
        yStart = Integer.parseInt(GetConfigProperties.getValue(fileName, "yStart"));
        xVerticies = Arrays.stream(GetConfigProperties.getValue(fileName, "xVertecies").split("-")).mapToInt(Integer::parseInt).toArray();
        yVerticies = Arrays.stream(GetConfigProperties.getValue(fileName, "yVertecies").split("-")).mapToInt(Integer::parseInt).toArray();
        xLanding = Arrays.stream(GetConfigProperties.getValue(fileName, "xLanding").split("-")).mapToInt(Integer::parseInt).toArray();
        yLanding = Arrays.stream(GetConfigProperties.getValue(fileName, "yLanding").split("-")).mapToInt(Integer::parseInt).toArray();
        resizeToScreen();
    }
    private LoadLevel() {
        throw new AssertionError();
    }

    private static void resizeToScreen(){
        xVerticies = Arrays.stream(xVerticies).map(x -> (int)(DefaultGameSettings.WIDTH*0.01*x)).toArray();
        yVerticies = Arrays.stream(yVerticies).map(y -> (int)(DefaultGameSettings.HEIGHT*0.01*y)).toArray();
        xLanding = Arrays.stream(xLanding).map(x -> (int)(DefaultGameSettings.WIDTH *0.01*x)).toArray();
        yLanding = Arrays.stream(yLanding).map(y -> (int)(DefaultGameSettings.HEIGHT *0.01*y)).toArray();
        xStart = (int)(xStart * 0.01 * DefaultGameSettings.WIDTH);
        yStart = (int)(yStart * 0.01 * DefaultGameSettings.HEIGHT);
    }

}
