package editor;

import game.Constant.DefaultGameSettings;
import game.Constant.GraphicsConstants;
import game.Constant.LoadLevel;
import game.Constant.MenuWindowStates;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.Terrain;
import game.menuPanels.AbstractVerticalPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.logging.Level;

public class Frame extends JFrame {
    public Frame() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        LevelPanel level = new LevelPanel();

        add(level);

        setVisible(true);
    }
}
