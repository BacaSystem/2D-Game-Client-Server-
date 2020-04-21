package server;

import game.data.HighScores;

public class Records implements Comparable<Records>{
    /** nick */
    private String nick;
    /** wynik punktowy*/
    private int score;
    public Records(String nick, int score) {
        this.nick = nick;
        this.score = score;
    }

    /**
     * metoda zwracająca wynik
     * @return zwraca wynik punktowy
     */
    public Integer getScore() {
        return this.score;
    }

    /**
     * Metoda zwracająca nick
     * @return zwraca nick
     */
    public String getNick() {
        return this.nick;
    }

    /**
     * Komparator, infoemuje, że podczas sortowania interesują nas porówywania wyników punktowych
     * @param record instancja klasy Record
     * @return zwraca wynik porownania dwóch rekordów
     */
    @Override
    public int compareTo(Records record) {
        return this.getScore().compareTo(record.getScore());
    }
}
