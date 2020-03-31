package game.data;


//Singleton class, Gracz. Istnieje tylko jedna jedyna instancja klasy Gracz. Przechouje nick i wynik aktualnego gracza
public class Player {
    private String  nick = "Maciej";
    private int score = 250;

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



    //"Niszczy gracza". Zeruje jego nick i wynik. Metodę tę należy wywołać zaraz po ty, gdy nie potrzebujemy już
    //danych naszego gracza
    public void destroyPlayer() {
        this.nick = "";
        this.score = 0;
    }
}
