package game;

import game.Constant.DefaultGameSettings;
import game.Constant.GraphicsConstants;
import game.Constant.LoadLevel;
import game.data.HighScores;
import game.data.Player;
import game.data.Points;
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

    BufferedImage gameOverImg, startImg, wonImage, crashedImg, landedImg, shipDestroyedImg, pauseImg;

    int currentLevel, maxLevels;
    boolean gameOver = false;
    boolean won = false;
    boolean crashed = false;
    boolean landed = false;
    boolean started = false;

    boolean savedScore = false;

    public int scoreOnWinOrLose = 0;

    public Ship ship;
    public Shape terrain;
    public Shape landing;
    private Collision detector;

    public Player player = Player.getInstance();
    public HighScores highScores = HighScores.getInstance();
    public game.data.Points points;

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
            shipDestroyedImg = ImageIO.read(new File(GraphicsConstants.SHIP_DESTROYED_IMAGE));
            pauseImg = ImageIO.read((new File(GraphicsConstants.PAUSE_IMAGE)));

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
        scoreOnWinOrLose = 0;
        if(crashed) {
            player.deleteOneLife();
        }
        if(landed) {
            if(currentLevel < maxLevels) {
                currentLevel++;
            }

            else {
                currentLevel = 1;
                player.resetLifes();
            }
        }
        if(gameOver) {
            player.resetLifes();
            currentLevel = 1;
        }
        won = false;
        gameOver = false;
        crashed = false;
        landed = false;
        started = false;
        savedScore = false;
        init();
    }

    public void update() {
        if (started) {
            if(player.getLifes() >= 0) {
                if (!crashed && !landed) {
                    ship.Update();
                    crashed = detector.detectCollisionTerrain();
                    landed = detector.detectWin();
                }
            }
            else {
                player.setLifesToZero();
                gameOver = true;
            }
        }

        while((landed || gameOver) && !savedScore) {
            savedScore = true;
            if (gameOver) {
                highScores.checkPlayerScore(player);
                scoreOnWinOrLose = player.getScore();
                player.resetPlayerScores();
            } else {
                if (currentLevel < maxLevels) {
                    Points.addPointsForLevel(player, ship.getFuel(), currentLevel);
                } else {
                    Points.addPointsForLevel(player,ship.getFuel(), currentLevel);
                    Points.bonusForLeftLifes(player,currentLevel);
                    highScores.checkPlayerScore(player);
                    scoreOnWinOrLose = player.getScore();
                    player.resetPlayerScores();
                }
            }
        }

        if(landed && currentLevel == maxLevels)
            won = true;

        if(player.getLifes() == 0 && crashed)
            gameOver = true;

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


        if(ship.pause)
            g.drawImage(pauseImg, 400,100,null);

        if(!started)
            g.drawImage(startImg, 400,100, null);

        if(gameOver){
            g.drawImage(gameOverImg, 200, 100, null);
        }

        if(won){
            g.drawImage(wonImage, 200,100, null);
        }

        if(crashed) {
            g.drawImage(shipDestroyedImg, ship.x, ship.y,null);
            g.drawImage(crashedImg, 0, 0, null);
            if(player.getLifes() != 0) {
                /* Może bez tego? Przez to może się ekran pierdzielic */ g.drawImage(startImg, 400,100,null);
                /* Może się czasem pokazywac   */
            }
        }

        if(landed)
            g.drawImage(landedImg, 0, 0, null);
    }
}
