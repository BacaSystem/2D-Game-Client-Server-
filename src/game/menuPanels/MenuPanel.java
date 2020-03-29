package game.menuPanels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import game.Constant.MenuWindowStates;
import game.Button;

public class MenuPanel extends JPanel {

    private String[] buttonNames = {MenuWindowStates.NEW_GAME_BUTTON, MenuWindowStates.HIGH_SCORES_BUTTON, MenuWindowStates.HELP_BUTTON, MenuWindowStates.EXIT_BUTTON};
    private String[] buttonActionNames = {MenuWindowStates.NEW_GAME, MenuWindowStates.HIGH_SCORES, MenuWindowStates.HELP, MenuWindowStates.EXIT};
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
        setLayout(new GridLayout(5, 1, 20, 20));
        this.add(panel);


        //setPreferredSize(new Dimension(panelWidth, panelHeight));


        this.add(panel);
    }
}
