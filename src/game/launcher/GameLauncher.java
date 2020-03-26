package game.launcher;

import javax.swing.*;

public class GameLauncher {

    public GameLauncher(){
        new LauncherWindow();

    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

}
