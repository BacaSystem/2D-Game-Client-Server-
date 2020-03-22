package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel {

    public static int width;
    public static int height;

    //Framerate
    public static final long NANOSEC = 1000000000L;
    public static final long MILISECINNANO = 1000000L;
    private final double FPS = 30;
    private final double UPDATE_TIME = NANOSEC / FPS;

    private State gameState;

    private double gameTime;
    private double lastTime;
    
    BufferedImage rocket;
    BufferedImage fireUp, fireDown, fireleft, fireRight;

    public Game() {
        this.setDoubleBuffered(true);
        this.setFocusable(true);

        gameState = State.Game;

        try {
            rocket = ImageIO.read(new File("resources/img/ship.png"));
            fireDown = ImageIO.read(new File("resources/img/fire_down.png"));
            fireleft  = ImageIO.read(new File("resources/img/fire_left.png"));
            fireRight = ImageIO.read(new File("resources/img/fire_right.png"));
            fireUp = ImageIO.read(new File("resources/img/fire_up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d)
    {
        g2d.fillRect(0,0,20,20);
        g2d.drawImage(rocket, 100 , 100, null);
        g2d.drawImage(fireDown, 100 , 100, null);
        g2d.drawImage(fireleft, 100 , 100, null);
        g2d.drawImage(fireRight, 100 , 100, null);
        g2d.drawImage(fireUp, 100 , 100, null);

    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        draw(g2d);
    }
}
