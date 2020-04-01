package game.data;


import game.Constant.DefaultGameSettings;

//Singleton class, Gracz. Istnieje tylko jedna jedyna instancja klasy Gracz. Przechouje nick i wynik aktualnego gracza
public class Player {
    private String  nick = "Maciej";
    private int score = 0;
    private int lifes = DefaultGameSettings.LIFES;

    private boolean isAllAdded=false;

    private Player() {
        if (Holder.INSTANCE != null) {
            throw new IllegalStateException("Singleton already constructed");
        }
    }

    //Aby dostać się do obiektu gracz, należy wywołać tę metodę statyczną. Przypisze to od zmiennej, nasz obiekt gracz.
    //Dzięki temu będziemy mogli się dostać do nicku i wyniku aktualnego gracza
    public static Player getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final Player INSTANCE = new Player();
    }

    //Ustawia nick aktualnego gracza
    public void setNick(String nick) {
        this.nick = nick;
    }

    //Pobiera nick aktualnego gracza
    public String getNick() {
        return this.nick;
    }

    //ustawia wynik aktualnego gracza
    public void setScore(int score) {
        this.score = score;
    }

    //Pobiera wynik aktualnego gracza
    public int getScore() {
        return this.score;
    }

    public void resetScore() {
        this.score = 0;
    }

    public int getLifes() { return this.lifes; }

    public void deleteOneLife() {
        if(lifes != 0) {
           lifes--;
        }
    }

    public void resetLifes() {
        lifes = DefaultGameSettings.LIFES;
    }

    public void setLifesToZero() {
        lifes = 0;
    }

    public void resetPlayerScores() {
        this.score = 0;
    }

    public void addPointsForLevel(float fuel, int levelNumber) {
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        float Z = fuel;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));

        int addPoints = (int) (Z*M) + K;
        score+= addPoints;
        System.out.println("Points added on level " + levelNumber + ": " + score);
    }

    public void addPointsAtLastLevel(float fuel, int levelNumber) {
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        float Z = fuel;
        int L = lifes;
        int S = DefaultGameSettings.S_POINTS;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));
        int addPoints = 0;
        if(score!=0) {
            addPoints += (int) (Z*M) + K + (int) ((float) L/ (float) 3 * S);
        }
        score+=  addPoints;
        System.out.println("Points added on LAST, " + levelNumber + ": " + score);
    }

    public void pointsAtTheEnd(int currentLevel) {
        int L = lifes;
        int S = DefaultGameSettings.S_POINTS;
        if (score != 0) {
            score+= (int)((float)L/(float)3 * S);
            System.out.println("added Extra Points For Ships at level "+ currentLevel +" AND exit to Menu: " + score);
        }
    }

    public int getLiveScore(float fuel, int levelNumber) {
        int accualScore = score;
        int liveScore = accualScore;
        int Z = (int) fuel;
        int L = lifes;
        int K = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "K"));
        int S = DefaultGameSettings.S_POINTS;
        int M = Integer.parseInt(GetConfigProperties.getValue("level" + levelNumber, "M"));
        liveScore+=  (int) (( (float) L/ (float) 3) * S) + (Z*M) + K;
        return liveScore;
    }


    //"Niszczy gracza". Zeruje jego nick i wynik. Metodę tę należy wywołać zaraz po ty, gdy nie potrzebujemy już
    //danych naszego gracza
    public void destroyPlayer() {
        this.nick = "";
        this.score = 0;
    }
}
