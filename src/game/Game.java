package game;

import game.Constant.DefaultGameSettings;
import game.controller.KeyHandler;
import game.window.GameWindow;

import javax.swing.*;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

/**
 * Głowna klasa panelu gry
 * Odpowiedzialna za renderowanie oraz update'owanie gry ze stałą częstotliwością
 */
public class Game extends JPanel implements Runnable {

    /** zmienna określająca szerokość okna */
    public static int width;
    /** zmienna określająca wysokość okna */
    public static int height;

    /** flaga określająca działanie bądź przerwanie pętli gry */
    public boolean running = false;
    /** flaga określająca wyświetlenie końcowego menu */
    public boolean hasPopUp = false;

    /** obiiekt okna gry
     * @see  GameWindow*/
    private GameWindow frame;
    /** obiekt managera gry - odpowiedzialny za logikę gry
     * @see GameManager*/
    private GameManager manager;
    /** obiekt KeyHandlera - odpowiedzialny za czytanie zdarzeń klawiatury
     * @see KeyHandler*/
    private KeyHandler key;

    /** wątek gry */
    private Thread gameThread;

    /** obiekt grafiki, do którego rysjemy inne obiekty */
    private Graphics g;

    /** zmienna określająca ilość klatek na sekundę */
    private int fps = 60;
    public int frameCount = 0;

    /**
     * Konstruktor klasy Game
     * Ustawia początkowe rozmiary okna oraz inicjuje potrzebne elementy
     * @param width początkowa szerokość okna
     * @param height początkowa wysokość okna
     * @param frame referencja do okna gry
     */
    public Game(int width, int height, GameWindow frame) {
        this.width = width;
        this.height = height;
        this.frame = frame;

        init();
    }

    /**
     * Metoda inicjująca niezbędne elementy
     * Tworzy obiekt grafiki, managera oraz KeyHandlera
     * @see GameManager
     * @see KeyHandler
     */
    public void init(){
        running = true;
        g = this.getGraphics();
        key = new KeyHandler(this);
        manager = new GameManager(this);
    }

    /**
     * Metoda tworząca okienko Pop Up po zakończeniu gry z informacją na temat jego wyniku
     * Implementuje dwa przyciski z ActionListenerem
     * Po wciśnięciu odpowiedniego przycisku gracz jest przenoszony do odpowiedniego stanu
     * menuButton - powrót do menu
     * trybutton - ponowne podejście do gry
     */
    public void showPopUp(){
        JFrame popUp = new JFrame();
        popUp.setTitle("");
        popUp.setSize(200,200);
        popUp.setLocationRelativeTo(null);
        popUp.setResizable(false);
        popUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popUp.setLayout(new FlowLayout());
        if(manager.won)
            popUp.add(new JLabel("Congratlations, You've won!"));
        else
            popUp.add(new JLabel("You've lost!"));
        popUp.add(new JLabel("Your Score was: " + manager.scoreOnWinOrLose));
        JButton menuButton = new JButton("Return to menu");
        JButton tryButton = new JButton("Try again");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manager.reload();
                popUp.dispose();
                hasPopUp = false;
                frame.goToMenu();
            }
        });
        tryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                manager.reload();
                hasPopUp = false;
                popUp.dispose();
            }
        });
        popUp.add(menuButton);
        popUp.add(tryButton);
        popUp.setVisible(true);
    }

    /**
     * Metoda pozwalająca zapisać wynik gracza i bezpiecznie wrócić do menu
     */
    private void saveAndGoToMenu(){
        manager.points.bonusForLeftLifes(manager.player, manager.currentLevel);
        manager.highScores.checkPlayerScore(manager.player);
        manager.player.resetPlayerScores();
        manager.player.resetLifes();
        frame.goToMenu();
    }

    /**
     * Metoda update()
     * Wywolywana ze stalą częstotliwością
     * Odpowiedzialna za update wszystkich elementów gry - wywołanie manager.update()
     * Na bieżąco ustala rozmiar okna gry
     * @see GameManager
     */
    public void update(){
        manager.update();

        width = getWidth();
        height = getHeight();

        if((manager.won || manager.gameOver) && !hasPopUp){
            hasPopUp = true;
            showPopUp();
        }
    }

    /**
     * Metoda input()
     * Wywolywana ze stałą częstotliwością
     * Odpowiedzialna za obsluge zdarzeń klawiatury w grze - wywolanie manager.inpu()
     * @see GameManager
     * @param key obiekt KeyHandler'a
     * @see KeyHandler
     */
    public void input(KeyHandler key){
        manager.input(key);

        if(key.escape.down())
            saveAndGoToMenu();
    }

    /**
     * Metoda render()
     * Wywoływana ze stałą częstotliwościa 60 razy na sekundę
     * Odpowiedzialna za rysowanie wszystkich obiektów gry do obiektu grafiki - wywołanie manager.render()
     * Dopasowuje rozmiar wszystkich obiektów do rozmiaru okna dynamicznie za pomoca przekształcenia affinicznego
     * @see GameManager
     * @param g obiekt grafiki do którego rysujemy wszstkie obiekty
     */
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.clearRect(0,0, getWidth(), getHeight());

        g2d.setColor(new Color(60, 60, 80));
        g2d.fillRect(0, 0, width, height);
        g2d.setColor(Color.white);
        AffineTransform saveTransform = g2d.getTransform();
        AffineTransform scaleMatrix = new AffineTransform();
        float sx =(1f+(getSize().width-DefaultGameSettings.WIDTH)/(float)DefaultGameSettings.WIDTH);
        float sy =(1f+(getSize().height-DefaultGameSettings.HEIGHT)/(float)DefaultGameSettings.HEIGHT);
        scaleMatrix.scale(sx, sy);
        g2d.setTransform(scaleMatrix);

        Font bigFont = new Font("Monospaced", Font.BOLD, 15);
        g2d.setFont(bigFont);

        g2d.drawString("FPS: " + fps, 5, 20);
        g2d.drawString("Current Level: " + manager.currentLevel, 5, 40);
        g2d.drawString("Lifes: " + manager.player.getLifes(), 5, 60);
        g2d.drawString("Fuel tank: " + manager.ship.getFuel(), 5, 80);
        g2d.drawString("Max Landing Speed: " + manager.ship.getMaxLandingSpeed(), 5, 100);
        g2d.drawString("Speed (X: " +  String.format("%.1f", manager.ship.getSpeedX()) + " Y: " + String.format("%.1f", manager.ship.getSpeedY()) + ")", 5, 120);
        if (!manager.crashed && !manager.landed) {
            g2d.drawString("Points:" + String.valueOf(manager.points.getLiveScore(manager.player, manager.ship.getFuel(), manager.currentLevel)), 5,140);
        } else{
            if(manager.landed && (manager.currentLevel != manager.maxLevels) || (manager.crashed && manager.player.getLifes() != 0)) {
                g2d.drawString("Points:" + String.valueOf(manager.player.getScore()), 5, 140);
            } else {
               g2d.drawString("Points:" + String.valueOf(manager.scoreOnWinOrLose),5,140);
            }
        }
        manager.render(g2d);

        g2d.setTransform(saveTransform);
        frameCount++;
    }

    /**
     * Metoda biblioteki swing, odpowiedzialna za rysowanie componentów graficznych
     * Wywolywana 60 razy na sekunde za pomoca wywolania repaint()
     * Wywoluje metode render()
     * @param g obiekt grafiki do którego rysowane są componenty
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
        g.dispose();
    }

    /**
     * Metoda biblioteki AWT
     * Tworzy nowy wątek gry
     */
    @Override
    public void addNotify(){
        super.addNotify();

        gameThread = new Thread(this, "Game Thread");
        gameThread.start();
    }

    /**
     * Metoda run z interfejsu Runnable
     * Główna pętla gry starająca utrzymać stałą częstotliwość odświeżania i renderowania gry
     * Wywołuje metody update, input, repaint ze stałą częstotliwością
     */
    @Override
    public void run() {
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            //Do as many game updates as we need to, potentially playing catchup.
            while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
            {
                update();
                input(key);
                lastUpdateTime += TIME_BETWEEN_UPDATES;
                updateCount++;
            }

            //If for some reason an update takes forever, we don't want to do an insane number of catchups.
            //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
            if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
            {
                lastUpdateTime = now - TIME_BETWEEN_UPDATES;
            }

            input(key);
            repaint();

            lastRenderTime = now;

            //Update the frames we got.
            int thisSecond = (int) (lastUpdateTime / 1000000000);
            if (thisSecond > lastSecondTime)
            {
                System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                fps = frameCount;
                frameCount = 0;
                lastSecondTime = thisSecond;
            }

            //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
            while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
                Thread.yield();

                //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                try {Thread.sleep(1);} catch(Exception e) {}

                now = System.nanoTime();
            }
        }
    }
}
