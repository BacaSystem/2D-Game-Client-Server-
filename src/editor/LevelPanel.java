package editor;

import game.Constant.LoadLevel;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.Terrain;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;

public class LevelPanel extends JPanel implements Runnable, Updatable {
    Terrain terrain;
    Socket serverSocket;

    public LevelPanel() {
        setSize(1280,720);
        LoadLevel.getLevel(serverSocket,1);
        terrain = new Terrain(LoadLevel.xVerticies, LoadLevel.yVerticies);

    }


    @Override
    public void render(Graphics2D g) {
        g.scale(0.5,0.5);
        terrain.render(g);
        //g.fillRect(1,1,100,100);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render((Graphics2D) g);
        g.dispose();
    }


    @Override
    public void update() {

    }

    @Override
    public void input(KeyHandler key) {

    }




    @Override
    public void run() {

    }
}
