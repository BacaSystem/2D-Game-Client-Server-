package editor;

import game.Constant.LoadLevel;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.Terrain;

import javax.swing.*;
import java.awt.*;

public class LevelPanel extends JPanel implements Runnable, Updatable {
    Terrain terrain;
    Graphics g;

    public LevelPanel() {
        JPanel panel = new JPanel();
        panel.setSize(1280,720);
        g = this.getGraphics();
        LoadLevel.getLevel(1);
        terrain = new Terrain(LoadLevel.xVerticies, LoadLevel.yVerticies);

        //this.add(new JLabel("ELO"));
    }

    @Override
    public void render(Graphics2D g) {
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
