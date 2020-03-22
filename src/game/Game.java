package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game extends JPanel implements KeyListener {

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

    private boolean isFireLeft = false, isFireRight = false, isFireUp = false, isFireDown = false;

    BufferedImage rocket;
    BufferedImage fireUp, fireDown, fireleft, fireRight;

    public Game() {
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        addKeyListener(this);

        gameState = State.Game;

        try {
            rocket = ImageIO.read(new File("img/ship.png"));
            fireDown = ImageIO.read(new File("img/fire_down.png"));
            fireleft  = ImageIO.read(new File("img/fire_left.png"));
            fireRight = ImageIO.read(new File("img/fire_right.png"));
            fireUp = ImageIO.read(new File("img/fire_up.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d)
    {
        g2d.fillRect(0,0,20,20);
        g2d.drawImage(rocket, 100 , 100, null);
        if(isFireDown) {
            g2d.drawImage(fireDown, 100 , 100, null);
        }
        if(isFireLeft) {
            g2d.drawImage(fireleft, 100 , 100, null);
        }
        if(isFireRight) {
            g2d.drawImage(fireRight, 100 , 100, null);
        }
        if(isFireUp) {
            g2d.drawImage(fireUp, 100 , 100, null);
        }


    }
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        draw(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key typed");
            isFireRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key typed");
            isFireLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key typed");
            isFireUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key typed");
            isFireDown = true;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
            isFireRight = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            isFireLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            isFireUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            isFireDown = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
            isFireLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key Released");
            isFireLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key Released");
            isFireUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key Released");
            isFireDown = false;
        }
    }
}
