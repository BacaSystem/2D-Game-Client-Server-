package game.menuPanels;
import game.Constant.MenuWindowStates;
import game.Button;
import game.data.GetConfigProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HelpPanel extends JPanel {

    public HelpPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        // setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("Pomoc drogowa"));
        add(new JLabel(getHelp("helpText", "tytul")));
        add(new JLabel(getHelp("helpText", "punkt1")));
        add(new JLabel(getHelp("helpText", "punkt2")));
        add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));
    }

    private String getHelp(String fileName, String key) {
        return GetConfigProperties.getValue(fileName, key);
    }
}
