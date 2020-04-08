package editor;

import game.Constant.LoadLevel;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.Terrain;

import javax.swing.*;
import java.awt.*;

public class Frame extends JPanel {
    Terrain terrain;
    Graphics graphics;
    public Frame (){
        JFrame frame  = new JFrame("Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
        frame.setVisible(true);

        frame.add(new LevelPanel());


    }


}
