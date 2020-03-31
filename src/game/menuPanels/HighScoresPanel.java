package game.menuPanels;
import game.data.HighScores;
import game.Constant.MenuWindowStates;
import game.entities.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class HighScoresPanel extends JPanel {
    private HighScores scoresData = HighScores.getInstance();
    private String[] nicks;
    private String[] scores;

    public HighScoresPanel(int panelWidth, int panelHeight, ActionListener menuListner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel verticalPanel = new JPanel(new GridLayout(10, 1, 10, 1));

        getData();
        //HERE add components
        for(int i=0; i<5; i++) {
            //System.out.println(nicks[i] + scores[i]);
            JPanel recordPanel = new JPanel();
            recordPanel.add(new JLabel((i+1) + "."));
            recordPanel.add(new JLabel(nicks[i]));
            recordPanel.add(new JLabel(scores[i] ,SwingConstants.RIGHT));
            verticalPanel.add(recordPanel);
        }

        verticalPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        //END OF COMPONENTS

        layout.add(verticalPanel);
        panel.add(layout, BorderLayout.CENTER);
        setLayout(new GridLayout(5, 1, 20, 20));
        this.add(panel);
        
    }

    void getData() {
        HighScores.updateData();
        nicks = new String[]{HighScores.NAME_1 ,HighScores.NAME_2, HighScores.NAME_3, HighScores.NAME_4, HighScores.NAME_5};
        scores = new String[]{HighScores.SCORE_1, HighScores.SCORE_2, HighScores.SCORE_3, HighScores.SCORE_4, HighScores.SCORE_5};

    }
}
