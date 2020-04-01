package game.entities;

/*
 * Rocket physics and controls.
*/

import game.Constant.GraphicsConstants;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship {

    public Rectangle2D collider;

    public int x; // X coordinate (2D)

    public int y; // Y coordinate (2D)

    private float fuel;

    private float startFuel;

    private int startX, startY;

    private double speedAccelerating; // Acceleration

    public double maxLandingSpeed; // Max speed for land

    public double speedX; // Horizontal speed

    public double speedY; // Vertical speed

    public double speedGrav; // Gravity

    private BufferedImage rocket; // Lunar Lander

    private BufferedImage distroyedRocket; //distroyed Lunar Lander

    private BufferedImage fireUp; // Lander going up Lander

    private BufferedImage fireDown; // Accelerating Lander

    private BufferedImage fireRight; // Lander flying left

    private BufferedImage fireLeft; // Lander flying right

    public boolean pause = false;

    private boolean up,down,right,left;

    double prevDx;
    double prevDY;

    public Ship(int x, int y, float gravity, float fuel) // Gather rocket dimensions
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

    private void initialize() {
        speedAccelerating = 0.5;
        speedY = 1;
        speedX = 0;
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

    public Rectangle2D getCollider(){
        return new Rectangle2D.Float(x+16,y+16,29,41);
    }

    public float getFuel(){
        return fuel;
    }

    public double getSpeedX(){
        return speedX;
    }

    public double getSpeedY(){
        return speedY;
    }

    public double getMaxLandingSpeed(){
        return maxLandingSpeed;
    }


    public void input(KeyHandler key){
        if(fuel > 0) {
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
        }else{
            up = false;
            down = false;
            right = false;
            left = false;
        }
    }

    public void Update() //// rocket controls
    {
        if(fuel <= 0)
            fuel = 0;

        if (up) {
            speedY -= speedAccelerating;
            fuel--;
        }

        else {
            speedY -= speedGrav;
        }

        if (down){
            speedY += speedAccelerating;
            fuel--;
        }
        if (left){ // Key RIGHT
            speedX -= speedAccelerating;
            fuel--;
        }
        if (right){ // Key LEFT
            speedX += speedAccelerating;
            fuel--;
        }
        if (pause) { // Pause
            speedY = 0;
            speedX = 0;
        }

        x += speedX;
        y += speedY;

        collider = getCollider();

    }


    public void render(Graphics2D g2d) {

        if (down) // Draw fly image
             g2d.drawImage(fireDown, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (up) // Draw fly image
            g2d.drawImage(fireUp, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (left) // Draw fly image
            g2d.drawImage(fireLeft, x, y, null);
        g2d.drawImage(rocket, x, y, null);

        if (right) // Draw fly image
            g2d.drawImage(fireRight, x, y, null);
        g2d.drawImage(rocket, x, y, null);
        g2d.setColor(Color.green);
    }
}


