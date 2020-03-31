package game.states;

import game.Constant.GraphicsConstants;
import game.Constant.LoadLevel;
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

    int currentLevel = 1;
    boolean gameOver = false;

    public Ship ship;
    public Shape terrain;
    public Shape landing;

    public PlayState(StatesManager manager){
        super(manager);
        init();
    }

    private void init(){
        LoadLevel.getLevel(currentLevel);
        terrain = new Polygon(LoadLevel.xVerticies, LoadLevel.yVerticies, LoadLevel.xVerticies.length);
        landing = new Polygon(LoadLevel.xLanding, LoadLevel.yLanding, LoadLevel.xLanding.length);
        ship = new Ship(LoadLevel.xStart, LoadLevel.yStart, LoadLevel.GRAVITY_SPEED);
    }

    @Override
    public void update() {
        if(!gameOver) {
            ship.Update();
        }
    }

    @Override
    public void input(KeyHandler key) {
        if(!gameOver)
            ship.input(key);

        if (key.escape.down) {
            gameOver = false;
        }
        if (key.space.down)
            ship.pause = true;
        else
            ship.pause = false;
        //else
            //paused = false;

    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fill(landing);
        g.setColor(Color.lightGray);
        g.fill(terrain);
        ship.render(g);

        if(gameOver)
            g.drawImage(gameOverImg, 200, 100, null);
    }
}
