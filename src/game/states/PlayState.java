package game.states;

import game.Constant.GraphicsConstants;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PlayState extends State{

    int x = 100, y = 100;

    BufferedImage rocket, gameOverImg;
    BufferedImage fireUp, fireDown, fireLeft, fireRight;

    boolean up = false, down = false, left = false, right = false, gameOver = false;

    public PlayState(StatesManager manager){
        super(manager);
        init();
    }

    private void init(){
        try {
            rocket = ImageIO.read(new File(GraphicsConstants.SHIP_IMAGE));
            fireDown = ImageIO.read(new File(GraphicsConstants.FIRE_DOWN_IMAGE));
            fireLeft  = ImageIO.read(new File(GraphicsConstants.FIRE_LEFT_IMAGE));
            fireRight = ImageIO.read(new File(GraphicsConstants.FIRE_RIGHT_IMAGE));
            fireUp = ImageIO.read(new File(GraphicsConstants.FIRE_UP_IMAGE));

            gameOverImg = ImageIO.read(new File(GraphicsConstants.GAME_OVER_IMAGE));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(!gameOver) {
            if (up)
                y--;
            if (down)
                y++;
            if (right)
                x++;
            if (left)
                x--;

            if (y >= 300 - 70)
                gameOver = true;
        }
    }

    @Override
    public void input(KeyHandler key) {
        if(!gameOver) {
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
        }

        if (key.escape.down) {
            gameOver = false;
            manager.add(StatesManager.MENU);
        }

    }

    @Override
    public void render(Graphics2D g) {


        g.drawImage(rocket, x, y, null);

        if(up)
            g.drawImage(fireUp, x, y, null);
        if(down)
            g.drawImage(fireDown, x, y, null);
        if(right)
            g.drawImage(fireRight, x, y, null);
        if(left)
            g.drawImage(fireLeft, x, y, null);
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 300, 1200, 30);
        if(gameOver)
            g.drawImage(gameOverImg, 200, 100, null);
    }
}
