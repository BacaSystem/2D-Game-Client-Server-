package editor;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LevelPanel level = new LevelPanel();

        add(level);

        setVisible(true);
    }
}
