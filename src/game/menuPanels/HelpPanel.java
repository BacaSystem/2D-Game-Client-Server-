package game.menuPanels;
import game.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    public HelpPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("Pomoc drogowa"));
        add(new Button(menuListner, "Back To Menu", "MainMenu"));
    }

    private JButton backToMenuButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("MainMenu");
        return backToMainMenuButton;
    }
}
