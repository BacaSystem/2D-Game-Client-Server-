package game.menuPanels;
import game.Constant.MenuWindowStates;
import game.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScoresPanel extends JPanel implements ActionListener {
    private JButton test;

    public HighScoresPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("NO SIEMA"));
        add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        test = (JButton) add(new JButton("Test button"));
        test.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var source = actionEvent.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(source == test){
                    System.out.println("TEŚCIK");
                }

            }
        });
    }
}
