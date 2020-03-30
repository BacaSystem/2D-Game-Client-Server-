package game.window;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameWindow extends JFrame  {


    public GameWindow(){
        this.setTitle("Lunar Lander");
        {
            this.setSize(1280, 720); // 1280x720px
            this.setLocationRelativeTo(null); // Centered
            this.setResizable(true); // Resizable
        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Stop thread when user exists frame

        Game game = new Game(1280, 720);
        add(game);

        this.setVisible(true);

    }



}
