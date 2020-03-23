package game;

import game.window.LauncherWindow;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

public class GameLauncher {

    public GameLauncher(){
        new LauncherWindow();

    }

    public static void main (String[] args) {
        //var player = new Player();
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

}
