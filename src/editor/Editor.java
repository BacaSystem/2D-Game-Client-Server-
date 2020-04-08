package editor;
import game.Constant.LoadLevel;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.*;

import game.Constant.LauncherConst;
import game.launcher.GameLauncher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Editor  {

    public static void main (String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() { new Frame();
            }
        });
    }
}
