package game;

/*
 * Rocket physics and controls.
*/

import game.Constant.GraphicsConstants;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship {

    public int x; // X coordinate (2D)

    public int y; // Y coordinate (2D)

    public boolean landed; // Check if landed

    public boolean crashed; // Check if crashed

    private double speedAccelerating; // Acceleration

    public double maxLandingSpeed; // Max speed for land

    private double speedX; // Horizontal speed

    public double speedY; // Vertical speed

    public double speedGrav; // Gravity

    private BufferedImage rocket; // Lunar Lander

    private BufferedImage rocketCrashed; // Crashed Lander

    private BufferedImage fireUp; // Lander going up Lander

    private BufferedImage fireDown; // Accelerating Lander

    private BufferedImage fireRight; // Lander flying left

    private BufferedImage fireLeft; // Lander flying right

    private boolean up,down,right,left,cheat;

    public Ship() // Gather rocket dimensions
    {
        initialize();
        loadcontent();

    }

    private void initialize() {
        x = 100;
        y = 100;
        speedAccelerating = 0.5;
        speedY = 1;
        speedGrav = -0.18;
        maxLandingSpeed = 5;
    }


    private void loadcontent() // Load resources
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

    public void input(KeyHandler key){
        if (key.up.down)
            up = true;
        else
            up = false;

        if (key.down.down)
            down = true;
        else
            down = false;

        if (key.right.down)
            right = true;
        else
            right = false;

        if (key.left.down)
            left = true;
        else
            left = false;
        if(key.cheat_0.down)
            cheat = true;
        else
            cheat = false;
    }

    public void Update() // rocket controls
    {
        if (up)
            speedY -= speedAccelerating;

        else {
            speedY -= speedGrav;
        }

        if (down){
            speedY += speedAccelerating;
        }
        if (left){ // Key RIGHT
            speedX -= speedAccelerating;
        }
        if (right){ // Key LEFT
            speedX += speedAccelerating;
        }
        if (cheat){ // Cheat
            speedY = 0;
            speedX = 0;
        }
        x += speedX;
        y += speedY;

    }


    public void render(Graphics2D g2d) {

        if (down) // Draw fly image
             g2d.drawImage(fireUp, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (up) // Draw fly image
            g2d.drawImage(fireDown, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (left) // Draw fly image
            g2d.drawImage(fireRight, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (right) // Draw fly image
            g2d.drawImage(fireLeft, x, y, null);
        g2d.drawImage(rocket, x, y, null);
    }
}


