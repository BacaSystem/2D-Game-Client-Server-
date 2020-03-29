package game.data;

import game.data.GetConfigProperties;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.valueOf;

public class HighScores {
    private static String fileName = "highScores";

    public static String SCORE_1;
    public static String NAME_1;
    public static String SCORE_2;
    public static String NAME_2;
    public static String SCORE_3;
    public static String NAME_3;
    public static String SCORE_4;
    public static String NAME_4;
    public static String SCORE_5;
    public static String NAME_5;


    private HighScores() {
        if (HighScores.Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    public static HighScores getInstance() {
        return HighScores.Holder.INSTANCE;
    }

    private static class Holder {
        private static final HighScores INSTANCE = new HighScores();
    }


    public static void updateData() {
        SCORE_1 = GetConfigProperties.getValue(fileName, "score1");
        NAME_1  = GetConfigProperties.getValue(fileName, "nick1");
        SCORE_2 = GetConfigProperties.getValue(fileName, "score2");
        NAME_2  = GetConfigProperties.getValue(fileName, "nick2");
        SCORE_3 = GetConfigProperties.getValue(fileName, "score3");
        NAME_3  = GetConfigProperties.getValue(fileName, "nick3");
        SCORE_4 = GetConfigProperties.getValue(fileName, "score4");
        NAME_4  = GetConfigProperties.getValue(fileName, "nick4");
        SCORE_5 = GetConfigProperties.getValue(fileName, "score5");
        NAME_5  = GetConfigProperties.getValue(fileName, "nick5");
    }

}
