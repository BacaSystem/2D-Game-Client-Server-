package game;

import game.states.State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game_ extends JPanel implements KeyListener, Runnable {

    public static int width;
    public static int height;

    private State gameState;
    private Thread gameThread;

    private int fps = 60;
    private int frameCount = 0;

    private int x = 300, y = 100;
    private boolean STOP = false;

    private boolean isFireLeft = false, isFireRight = false, isFireUp = false, isFireDown = false;

    BufferedImage rocket;
    BufferedImage fireUp, fireDown, fireleft, fireRight;

    public Game_(int panelWidth, int panelHeight, ActionListener menuListner) {

        setPreferredSize(new Dimension(panelWidth, panelHeight));

        this.setDoubleBuffered(true);


        add(backToMenuButton(menuListner));

        //this.setFocusable(true);
        //this.requestFocusInWindow();


        addKeyListener(this);

        init();
        this.setVisible(true);
    }

    private void init(){
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

    public void stopTheGame() {
        this.STOP = true;
    }

    public void update(){

    }

    private JButton backToMenuButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("BackToMainMenuFromGamePanel");
        return backToMainMenuButton;
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        draw(g2d);
    }

    public void draw(Graphics2D g2d) {
        g2d.drawString("FPS: " + fps, 5, 10);
        frameCount++;

        g2d.drawImage(rocket, x, y, null);
        if (isFireDown) {
            g2d.drawImage(fireDown, x, y, null);
        }
        if (isFireLeft) {
            g2d.drawImage(fireleft, x, y, null);
        }
        if (isFireRight) {
            g2d.drawImage(fireRight, x, y, null);
        }
        if (isFireUp) {
            g2d.drawImage(fireUp, x, y, null);
        }
    }

    @Override
    public void addNotify(){
        super.addNotify();

        gameThread = new Thread(this);
        gameThread.start();
    }

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

        boolean paused = false;

        while (!STOP)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused)
            {
                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {
                    update();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

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
            x++;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
            isFireLeft = true;
            x--;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("Up key pressed");
            isFireUp = true;
            y--;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("Down key pressed");
            isFireDown = true;
            y++;
        }

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
            isFireRight = false;
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
