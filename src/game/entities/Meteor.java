package game.entities;

import game.Constant.GraphicsConstants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Meteor {

    private Rectangle2D meteor;
    private int x,y;
    private float speedX, speedY;
    private float mass;
    private float gravity;
    private BufferedImage meteorImg;
    public boolean pause = false;

    public Meteor(int x, int y, float mass, float vetricalSpeed, float gravity){
        this.x = x;
        this.y = y;
        this.mass = mass;
        this.gravity = (float) (gravity/9.81 *(-0.18));;

        speedX = vetricalSpeed;

        loadResources();
    }

    private void loadResources(){
        try {
            meteorImg = ImageIO.read(new File(GraphicsConstants.METEOR_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        meteor = new Rectangle2D.Float(x,y,60,60);

        if(!pause) {
            speedY -= gravity * mass/100;

            x += speedX;
            y += speedY;
        }
    }

    public void render(Graphics2D g){
        g.drawImage(meteorImg, x, y, null);
    }

    public Rectangle2D getCollider(){
        return meteor;
    }

}
