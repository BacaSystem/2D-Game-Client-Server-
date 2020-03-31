package game.data;

import java.util.HashMap;
import java.util.Map;

public class HighScores {
    private final String fileName = "highScores";
    private String[] nicks;
    private int[] scores;
    private final int numberOfRecords = 5;
    private boolean isDataDonwloaded = false;

    //----------------------------------
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
    //-----------------------------------

    public String[] getNicks() {
        return nicks;
    }

    public int[] getScores() {
        return scores;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void downloadData() {
        if (!isDataDonwloaded) {
            nicks = null;
            scores = null;
            nicks = new String[numberOfRecords];
            scores = new int[numberOfRecords];
            for(int i=0; i<numberOfRecords; i++) {
                String nickKey = "nick" + (i+1);
                String scoreKey = "score" + (i+1);
                nicks[i] = GetConfigProperties.getValue(fileName, nickKey);
                scores[i] = Integer.parseInt(GetConfigProperties.getValue(fileName, scoreKey));
                System.out.println(nicks[i] + " " + scores[i]);
            }
            isDataDonwloaded = true;
        }
    }
/*
    public void checkPlayerScore(Player player) {
        Map<String, Integer> players = new HashMap<String, Integer>();

        for(String ) {
            players.put(nicks[i], scores[i])
        }
        krotka[numberOfRecords] = new Krotka(player.getNick(), player.getScore());



    }
    */
}
