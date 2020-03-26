package game.menu;

import game.window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HighScores extends JPanel implements ActionListener {
    private JButton test;

    public HighScores(int panelWidth, int panelHeight, ActionListener menuListner) {
        setPreferredSize(new Dimension(panelWidth, panelHeight));

        add(new JLabel("NO SIEMA"));
        add(backToMenuButton(menuListner));

        test = (JButton) add(new JButton("Test button"));
        test.addActionListener(this);
        
    }

    private JButton backToMenuButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Back to Menu");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("BackToMainMenuFromHighScoresPanel");
        return backToMainMenuButton;
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
