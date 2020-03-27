package game.menuPanels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import game.Button;

public class MenuPanel extends JPanel {
    private JLabel GameNameLabel;

    private String[] buttonNames = {"New Game", "High Scores", "Help", "Exit"};
    private String[] buttonActionNames = {"NewGame", "HighScores", "Help", "Exit"};
    private int howManyButtons = buttonActionNames.length;

    public MenuPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        GameNameLabel = new JLabel("LANDER", SwingConstants.CENTER);

        for (var i = 0; i < howManyButtons; i++) {
            var button = new Button(menuListner, buttonNames[i], buttonActionNames[i]);
            add(button);
        }
    }
}
