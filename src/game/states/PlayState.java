package game.states;

import game.Constant.GraphicsConstants;
import game.Ship;
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

    boolean paused = false, gameOver = false;

    public Ship ship;

    public PlayState(StatesManager manager){
        super(manager);
        ship = new Ship();
        init();
    }

    private void init(){

    }

    @Override
    public void update() {
        if(!gameOver || !paused) {
            ship.Update();
        }
    }

    @Override
    public void input(KeyHandler key) {
        if(!gameOver || !paused)
            ship.input(key);

        if (key.escape.down) {
            gameOver = false;
            paused = false;
        }
        if (key.space.down) {
            paused = true;
            System.out.println("PAUSED!");
        }
        //else
            //paused = false;

    }

    @Override
    public void render(Graphics2D g) {
        ship.render(g);
        g.setColor(new Color(255,255,255));
        g.fillRect(0, 300, 1200, 30);
        if(gameOver)
            g.drawImage(gameOverImg, 200, 100, null);
    }
}
