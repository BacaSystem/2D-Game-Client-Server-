package game.entities;

/*
 * Rocket physics and controls.
*/

import game.Constant.DefaultGameSettings;
import game.Constant.GraphicsConstants;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Klasa statku, reprezentująca obiekt gracza w grze
 * Reaguje na zdarzenia z klawiatury, oraz jest update'owana razem z grą
 */
public class Ship {
    /** obiekt określający granice statku - implementajca kolicji */
    public Rectangle2D collider;
    /** współrzędne startowe x i y statku na mapie */
    private int startX, startY;
    /** startowa ilość paliwa */
    private float startFuel;
    /** aktualne współrzędne x i y statku na mapie */
    public int x, y;
    /** aktualna ilość paliwa */
    private float fuel;
    /** moc silników */
    private double speedAccelerating;
    /** maksymalna dopuszczalna prędkość lądowania */
    public double maxLandingSpeed;
    /** aktualna prędkość statku w osi x i y */
    public double speedX, speedY;
    /** siła grawitacji */
    public double speedGrav;
    /** obrazy reprezentujące statek oraz prace silników */
    private BufferedImage rocket, fireUp, fireDown, fireRight, fireLeft;
    /** flaga określająca czy gra jest w trybie pauzy */
    public boolean pause = false;
    /** zmienne reprezentujące przyciski klawiatury */
    private boolean up = false, down = false, right = false, left = false;

    /**
     * Konstruktor klasy statek, przypisuje statkowi startowe wartości podane jako parametry oraz wczytuje jego grafiki
     * @param x startowa współrzędna x statka na mapie
     * @param y startowa współrzędna y statka na mapie
     * @param gravity wartość siły grawitacji działająca na statek
     * @param fuel startowa wartość paliwa statku
     */
    public Ship(int x, int y, float gravity, float fuel)
    {
        startX = x;
        startY = y;
        startFuel = fuel;

        this.x = startX;
        this.y = startY;
        this.fuel = startFuel;

        collider = getCollider();
        speedGrav = gravity/9.81 *(-0.18);

        initialize();
        loadcontent();
    }

    /**
     * Metoda ustawiająca początkowe wartości dla
     * mocy silników, maksymalnej prędkości lądowania i początkowej prędkości statku
     */
    public void initialize() {
        speedAccelerating = DefaultGameSettings.SPEED_ACCELERATING;
        speedY = DefaultGameSettings.START_SPEED_Y;
        speedX = DefaultGameSettings.START_SPEED_X;
        maxLandingSpeed = DefaultGameSettings.MAX_SPEED_LANDING;
        up = false;
        down = false;
        right = false;
        left = false;
    }


    /**
     * Meotda wczytująca grafiki
     */
    private void loadcontent()
    {
        try {
            rocket = ImageIO.read(new File(GraphicsConstants.SHIP_IMAGE));
            fireDown = ImageIO.read(new File(GraphicsConstants.FIRE_DOWN_IMAGE));
            fireLeft  = ImageIO.read(new File(GraphicsConstants.FIRE_LEFT_IMAGE));
            fireRight = ImageIO.read(new File(GraphicsConstants.FIRE_RIGHT_IMAGE));
            fireUp = ImageIO.read(new File(GraphicsConstants.FIRE_UP_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zwracająca prostokąt reprezentujący rozmiary statku
     * @return prostokąt będący rozmiarem statku
     */
    public Rectangle2D getCollider(){
        return new Rectangle2D.Float(x+16,y+16,29,41);
    }

    /**
     * Metoda zwracająca aktualną ilość paliwa
     * @return aktualna wartośc paliwa
     */
    public float getFuel(){
        return fuel;
    }

    /**
     * Metoda zwracająca aktualną prędkość w osi x
     * @return prędkość w osi x
     */
    public double getSpeedX(){
        return speedX;
    }

    /**
     * Metoda zwracająca aktualną prędkość w osi y
     * @return prędkość w osi y
     */
    public double getSpeedY(){
        return speedY;
    }

    /**
     * Metoda zwracająca maksymalną dopuszczalną prędkość do lądowania
     * @return maksymalna dopuszczalna prędkość lądowania
     */
    public double getMaxLandingSpeed(){
        return maxLandingSpeed;
    }

    /**
     * Metoda odpowiedzialna za obsługe zdarzeń z klawiatury, wywoływana razem z updtae()
     * ustawia odpowiednią flagę dla nacisniętego przycisku
     * @param key KeyHandler - obiekt obsługujący zdarzenia klawiatury
     */
    public void input(KeyHandler key){

        if(!pause) {
            if (fuel > 0) {
                if (key.up.down())
                    up = true;
                else
                    up = false;

                if (key.down.down())
                    down = true;
                else
                    down = false;

                if (key.right.down())
                    right = true;
                else
                    right = false;

                if (key.left.down())
                    left = true;
                else
                    left = false;
            } else {
                up = false;
                down = false;
                right = false;
                left = false;
            }
        }
    }

    /**
     * Metoda Update odpowiedzialna za wszelkie wyliczenia pozycji, prędkości, paliwa, itd.
     * Zmienia wartości prędkości w zalezności od wciśnietego przycisku, co również wywołuje dekrementacje ilości paliwa
     */
    public void Update()
    {

        if(!pause) {

            //System.out.println("pause: " + pause);
            if (fuel <= 0)
                fuel = 0;

            if (up) {
                speedY -= speedAccelerating;
                fuel--;
            } else {
                speedY -= speedGrav;
            }

            if (down) {
                speedY += speedAccelerating;
                fuel--;
            }
            if (left) { // Key RIGHT
                speedX -= speedAccelerating;
                fuel--;
            }
            if (right) { // Key LEFT
                speedX += speedAccelerating;
                fuel--;
            }

            x += speedX;
            y += speedY;
        }

        collider = getCollider();

    }

    /**
     * Metoda odpowiedzialna za rysowanie statku do obiektu grafiki
     * @param g2d obiekt grafiki do którego rysowane są elementy
     */
    public void render(Graphics2D g2d) {

        if (down) // Draw fly image
             g2d.drawImage(fireDown, x, y, null);

        if (up) // Draw fly image
            g2d.drawImage(fireUp, x, y, null);

        if (left) // Draw fly image
            g2d.drawImage(fireLeft, x, y, null);

        if (right) // Draw fly image
            g2d.drawImage(fireRight, x, y, null);

        g2d.drawImage(rocket, x, y, null);
    }
}


