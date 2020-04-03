package game.launcher;

import game.data.GetConfigProperties;

import javax.swing.*;
import java.io.IOException;
/**
 * Klasa główna launchera gry. Wywołuje konstruktor okna launchera. Zawiera main()!
 */
public class GameLauncher {

    public GameLauncher(){
        new LauncherWindow();

    }

    public static void main (String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

}
