package game.data;


import game.Constant.DefaultGameSettings;

//Singleton class, Gracz. Istnieje tylko jedna jedyna instancja klasy Gracz. Przechouje nick i wynik aktualnego gracza
/**
 * Klasa gracza.
 * Singletone.
 * Przechowywuje ona informacje o aktualnie grajacym graczu
 */
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

    public void resetPlayerScores() { this.score = 0; }

    public void addPoints(int points) { this.score+=points; }

    public int getLifes() { return this.lifes; }

    public void resetLifes() {
        lifes = DefaultGameSettings.LIFES;
    }

    public void setLifesToZero() {
        lifes = 0;
    }

    public void deleteOneLife() {
        if(lifes != 0) {
           lifes--;
        }
    }

}
