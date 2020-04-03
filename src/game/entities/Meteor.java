package game.entities;

import game.Constant.GraphicsConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Klasa reprezentująca meteoryt
 */
public class Meteor {

    /** obiekt określający rozmiary meteoru - implementajca kolicji */
    private Rectangle2D meteor;
    /** aktualne współrzędne x i y meteoru na mapie */
    public int x,y;
    /** aktualne prędkości meteoru, kolejno w osi x i y */
    private float speedX, speedY;
    /** masa meteoru */
    private float mass;
    /** siła przyciągania meteoru do planety */
    private float gravity;
    /** obraz reprezentujący obiekt meteoru */
    private BufferedImage meteorImg;
    /** flaga określająca stan pauzy */
    public boolean pause = false;

    /**
     * Konstruktor klasy meteor, przypisuje początkowe wartości oraz ładuje zasoby
     * @param x współrzędna x obiektu
     * @param y współrzędna y obiektu
     * @param mass masa obiektu
     * @param vetricalSpeed prędkość obiektu w osi x
     * @param gravity siła grawitacji
     */
    public Meteor(int x, int y, float mass, float vetricalSpeed, float gravity){
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.gravity = (float) (gravity/9.81 *(-0.18));;

        speedX = vetricalSpeed;

        loadResources();
    }

    /** Metoda ładująca zasoby - obraz obiektu */
    private void loadResources(){
        try {
            meteorImg = ImageIO.read(new File(GraphicsConstants.METEOR_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda update, wywolywana ze stałą częstotliwością
     * Odpowiada za ustalenie pozycji i poprawego collidera obiektu
     */
    public void update(){
        meteor = new Rectangle2D.Float(x, y, meteorImg.getWidth(), meteorImg.getHeight());

        if(!pause) {
            speedY -= gravity * mass/100;

            x += speedX;
            y += speedY;
        }
    }

    /**
     * Metoda render, wywolywana ze stałą częstotliwością
     * Odpowiada ze rysowanie obiektu meteoru do obiektu grafiki
     * @param g obiekt grafiki do którego rysowany jest obiekt
     */
    public void render(Graphics2D g){
        g.drawImage(meteorImg, x, y, null);
    }

    /**
     * Metoda zwracająca prostokąt będący reprezentacją obiektu dla kolizji
     * @return prostokąt reprezentujący obiekt
     */
    public Rectangle2D getCollider(){
        return meteor;
    }
}
