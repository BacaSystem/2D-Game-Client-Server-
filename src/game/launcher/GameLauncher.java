package game.launcher;

import game.Constant.DefaultGameSettings;
import game.Constant.GraphicsConstants;
import game.Constant.MenuWindowStates;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

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
