package game.data;

import game.Constant.DefaultGameSettings;

public class Points {
    public Points() { }


    public static void addPointsForLevel(Player player, float fuel, int levelNumber) {
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        float Z = fuel;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));

        int addPoints = (int) (Z*M) + K;
        player.addPoints(addPoints);

        //score+= addPoints;
        System.out.println("Points added on level " + levelNumber + ": " + player.getScore());
    }

    public static void addPointsAtLastLevel(Player player, float fuel, int levelNumber) {
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        float Z = fuel;
        int L = player.getLifes();
        int S = DefaultGameSettings.S_POINTS;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));
        int addPoints = 0;
        if(player.getScore()!=0) {
            addPoints += (int) (Z*M) + K + (int) ((float) L/ (float) 3 * S);
        }
        player.addPoints(addPoints);

        //score+=  addPoints;
        System.out.println("Points added on LAST, " + levelNumber + ": " + player.getScore());
    }

    public static void pointsAtTheEnd(Player player, int currentLevel) {
        int L = player.getLifes();
        int S = DefaultGameSettings.S_POINTS;
        if (player.getScore() != 0) {
            player.addPoints((int)((float)L/(float)3 * S));

            //score+= (int)((float)L/(float)3 * S);
            System.out.println("added Extra Points For Ships at level "+ currentLevel +" AND exit to Menu: " + player.getScore());
        }
    }

    public static int getLiveScore(Player player, float fuel, int levelNumber) {
        int accualScore = player.getScore();
        int liveScore = accualScore;
        int Z = (int) fuel;
        int L = player.getLifes();
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        int S = DefaultGameSettings.S_POINTS;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));
        liveScore+=  (int) (( (float) L/ (float) 3) * S) + (Z*M) + K;
        return liveScore;
    }

}
