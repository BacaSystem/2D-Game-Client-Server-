package game;

import game.Constant.DefaultGameSettings;
import game.Constant.GraphicsConstants;
import game.Constant.LoadLevel;
import game.data.HighScores;
import game.data.Player;
import game.entities.Ship;
import game.controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

//przepraszam za kod wygrania, to jest straszne wiem xD
public class GameManager {

    BufferedImage gameOverImg, startImg, wonImage, crashedImg, landedImg;

    int currentLevel, maxLevels;
    boolean gameOver = false;
    boolean won = false;
    boolean crashed = false;
    boolean landed = false;
    boolean started = false;

    //public int lifes;

    public Ship ship;
    public Shape terrain;
    public Shape landing;
    private Collision detector;

    public Player player = Player.getInstance();
    public HighScores highScores = HighScores.getInstance();

    public GameManager(JPanel game){
        currentLevel = 1;
        //lifes = DefaultGameSettings.LIFES;
        init();
    }

    private void init(){
        try {
            gameOverImg = ImageIO.read(new File(GraphicsConstants.GAME_OVER_IMAGE));
            startImg = ImageIO.read(new File((GraphicsConstants.MENU_TEXT_IMAGE)));
            wonImage = ImageIO.read(new File(GraphicsConstants.YOU_WON_IMAGE));
            landedImg = ImageIO.read(new File(GraphicsConstants.LANDED_IMAGE));
            crashedImg = ImageIO.read(new File(GraphicsConstants.CRASHED_IMAGE));

            maxLevels = DefaultGameSettings.NUMBEROFLEVELS;

            LoadLevel.getLevel(currentLevel);
            terrain = new Polygon(LoadLevel.xVerticies, LoadLevel.yVerticies, LoadLevel.xVerticies.length);
            landing = new Polygon(LoadLevel.xLanding, LoadLevel.yLanding, LoadLevel.xLanding.length);
            ship = new Ship(LoadLevel.xStart, LoadLevel.yStart, LoadLevel.GRAVITY_SPEED, DefaultGameSettings.FUEL);
            detector = new Collision(ship, terrain, landing);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload(){
        if(crashed)
            //lifes --;
            player.deleteOneLife();
        if(landed) {
            if(currentLevel < maxLevels) {
                //----------------!!!!!!!!!!!!!!!!!!!!--------------//
                player.addPointsForLevel(ship.getFuel(), currentLevel);
                currentLevel++;
            }

            else {
                //----------------!!!!!!!!!!!!!!!!!!!!--------------//
                //player.addPointsForLevel(ship.getFuel(),currentLevel);
                player.addPointsAtLastLevel(ship.getFuel(), currentLevel);
                highScores.checkPlayerScore(player);
                player.resetPlayer();
                currentLevel = 1;
                //lifes = DefaultGameSettings.LIFES;
            }
        }
        if(gameOver) {
            player.pointsAtTheEnd(currentLevel);
            highScores.checkPlayerScore(player);
            player.resetPlayer();
            currentLevel = 1;
            //lifes = DefaultGameSettings.LIFES;
        }
        gameOver = false;
        crashed = false;
        landed = false;
        started = false;
        init();
    }

    public void update() {
        if (started) {
            //if(lifes >= 0) {
            if(player.getLifes() >= 0) {
                if (!crashed && !landed) {
                    ship.Update();
                    crashed = detector.detectCollisionTerrain();
                    landed = detector.detectWin();
                }
            }
            else {
                player.setLifesToZero();
                //lifes = 0;
                gameOver = true;
            }
        }
    }

    public void input(KeyHandler key) {

        if(!gameOver) {
            if (!started) {
                if (key.enter.down)
                    started = true;
            } else {
                if (!crashed && !landed)
                    ship.input(key);
                else {
                    if (key.enter.down) {
                        reload();
                    }
                }

                if (key.escape.down) {
                    crashed = false;
                }

                if (key.space.down)
                    ship.pause = true;
                else
                    ship.pause = false;
            }
        }
        else{
            if(key.enter.down)
                reload();
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

        //if(lifes == 0 && crashed) {
        if(player.getLifes() == 0 && crashed) {
            gameOver = true;
            g.drawImage(gameOverImg, 200, 100, null);
        }

        if(landed && currentLevel == maxLevels){
            won = true;
            g.drawImage(wonImage, 200,100, null);
        }

        if(crashed)
            g.drawImage(crashedImg, 0, 0, null);

        if(landed)
            g.drawImage(landedImg, 0, 0, null);
    }

    public void saveScoreAfterGame() {
        player.addPointsForLevel(ship.getFuel(),currentLevel);
        highScores.checkPlayerScore(player);
    }

}
