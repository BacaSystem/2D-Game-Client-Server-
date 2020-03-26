package game.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpMenu extends JPanel {
    public HelpMenu(int panelWidth, int panelHeight, ActionListener menuListner) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("Pomoc drogowa"));
        add(backToMenuButton(menuListner));
    }

    private JButton backToMenuButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("BackToMainMenuFromHelpPanel");
        return backToMainMenuButton;
    }
}
