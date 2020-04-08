package editor;

import game.Constant.LauncherConst;
import game.launcher.GameLauncher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Editor {

    public Editor (){
        JFrame frame  = new JFrame("Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1280,720);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
            frame.setVisible(true);
    }

    public static void main (String[] args) throws IOException {
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new Editor();
            }
        });
    }
}
