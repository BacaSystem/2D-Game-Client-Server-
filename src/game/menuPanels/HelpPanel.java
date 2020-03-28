package game.menuPanels;
import game.Constant.MenuWindowStates;
import game.Button;
import game.data.GetConfigProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class HelpPanel extends JPanel {
    private GridBagLayout grid = new GridBagLayout();

    public HelpPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
       // setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("Pomoc drogowa"));
        add(new JLabel(getHelp("helpText", "tytul")), grid);
        add(new JLabel(getHelp("helpText", "punkt1")), grid);
        add(new JLabel(getHelp("helpTextf", "punkt2")), grid);
        add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU), grid);

        setLayout(grid);
    }


    private String getHelp(String fileName, String key) {
        return GetConfigProperties.getValue(fileName, key);
    }
}
