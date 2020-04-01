package game.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class HighScores {
    private static final String fileName = "highScores";
    private ArrayList<Record> records = new ArrayList<Record>(numberOfRecords+1);
    private static int numberOfRecords = Integer.parseInt(GetConfigProperties.getValue(fileName, "numerOfRecords"));
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


    //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-
    public class Record implements Comparable<Record>{
        private String nick;
        private int score;
        public Record(String nick, int score) {
            this.nick = nick;
            this.score = score;
        }
        public Integer getScore() {
            return this.score;
        }
        public String getNick() {
            return this.nick;
        }

        @Override
        public int compareTo(Record record) {
            return this.getScore().compareTo(record.getScore());
        }
    }
    //-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-

    public ArrayList<Record> getRecords() {
        return records;
    }

    public int getNumberOfRecords() {
        return numberOfRecords;
    }

    public void downloadData() {
        if (!isDataDonwloaded) {
            for(int i=0; i<numberOfRecords; i++) {
                String nickKey = "nick" + (i+1);
                String scoreKey = "score" + (i+1);
                var nick = GetConfigProperties.getValue(fileName, nickKey);
                var score = Integer.parseInt(GetConfigProperties.getValue(fileName, scoreKey));
                records.add(new Record(nick, score));
            }
            isDataDonwloaded = true;
        }
    }

    public void checkPlayerScore(Player player) {
        this.downloadData();
        if(player.getScore() >= records.get(numberOfRecords - 1).getScore()) {
            records.add(new Record(player.getNick(), player.getScore()));
            records.sort(Record::compareTo);
            Collections.reverse(records);
            records.remove(numberOfRecords);
            GetConfigProperties.seveScoresTableInDirectory(records, fileName, numberOfRecords);
            System.out.println("Ranging reloaded");
        }
    }
}
