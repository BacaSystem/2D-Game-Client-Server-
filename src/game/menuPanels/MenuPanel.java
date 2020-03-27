package game.menuPanels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import game.Button;

public class MenuPanel extends JPanel {

    private String[] buttonNames = {"New Game", "High Scores", "Help", "Exit"};
    private String[] buttonActionNames = {"NewGame", "HighScores", "Help", "Exit"};
    private int howManyButtons = buttonActionNames.length;

    public MenuPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));
        buttonPanel.add(new JLabel("LANDER", SwingConstants.CENTER));
        for (var i = 0; i < howManyButtons; i++) {
            buttonPanel.add(new Button(menuListner, buttonNames[i], buttonActionNames[i]));

        }

        layout.add(buttonPanel);
        panel.add(layout, BorderLayout.CENTER);
        this.add(panel);


        //setPreferredSize(new Dimension(panelWidth, panelHeight));


        this.add(panel);
    }
}
