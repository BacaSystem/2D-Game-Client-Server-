package game.launcher;

import game.data.GetConfigProperties;

import javax.swing.*;
import java.io.IOException;

public class GameLauncher {

    public GameLauncher(){
        new LauncherWindow();

    }

    public static void main (String[] args) throws IOException {

        //System.out.println(GetConfigProperties.getValue("sized", "height"));

        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

}
