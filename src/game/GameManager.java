package game;

import game.Constant.GraphicsConstants;
import game.Constant.LoadLevel;
import game.entities.Ship;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameManager {

    BufferedImage gameOverImg, startImg;

    int currentLevel;
    boolean gameOver = false;
    boolean won = false;
    boolean started = false;

    public Ship ship;
    public Shape terrain;
    public Shape landing;
    private Collision detector;

    public GameManager(){
        currentLevel = 1;
        init();
    }

    private void init(){
        try {
            gameOverImg = ImageIO.read(new File(GraphicsConstants.GAME_OVER_IMAGE));
            startImg = ImageIO.read(new File((GraphicsConstants.MENU_TEXT_IMAGE)));

            LoadLevel.getLevel(currentLevel);
            terrain = new Polygon(LoadLevel.xVerticies, LoadLevel.yVerticies, LoadLevel.xVerticies.length);
            landing = new Polygon(LoadLevel.xLanding, LoadLevel.yLanding, LoadLevel.xLanding.length);
            ship = new Ship(LoadLevel.xStart, LoadLevel.yStart, LoadLevel.GRAVITY_SPEED, LoadLevel.fuelAmount);
            detector = new Collision(ship, terrain, landing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        ship.reload();
        started = false;
        gameOver = false;
        won = false;
    }

    public void update() {
        if(started){
            if (!gameOver && !won) {
                ship.Update();
                gameOver = detector.detectCollisionTerrain();
                won = detector.detectWin();
            }else {
                System.out.println("Won " + won);
            }
        }
    }

    public void input(KeyHandler key) {
        if(!started){
            if(key.enter.down)
                started = true;
        }
        else {
            if (!gameOver && !won)
                ship.input(key);
            else {
                if (key.enter.down) {
                    reload();
                }
            }

            if (key.escape.down) {
                gameOver = false;
            }

            if (key.space.down)
                ship.pause = true;
            else
                ship.pause = false;
        }
    }

    public void render(Graphics2D g) {
        g.setColor(Color.gray);
        g.fill(landing);
        g.setColor(Color.lightGray);
        g.fill(terrain);
        ship.render(g);

        if(!started)
            g.drawImage(startImg, 400,100, null);

        if(gameOver || won)
            g.drawImage(gameOverImg, 200, 100, null);
    }

}
