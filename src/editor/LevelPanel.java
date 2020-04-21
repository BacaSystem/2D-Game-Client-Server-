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
        LoadLevel.getLevel(serverSocket,2);
        terrain = new Terrain(LoadLevel.xVerticies, LoadLevel.yVerticies);
        setPreferredSize(new Dimension(400,200));
    }


    @Override
    public void render(Graphics2D g) {
        g.scale(0.5,0.5);
        terrain.render(g);

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
