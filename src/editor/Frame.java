package editor;

import javax.swing.*;

public class Frame extends JFrame {

    public LevelPanel level;

    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        level = new LevelPanel();

        add(level);

        setVisible(true);
    }
}
