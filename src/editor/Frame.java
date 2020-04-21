package editor;

import game.Constant.LoadLevel;
import game.Updatable;
import game.controller.KeyHandler;
import game.entities.Terrain;
import game.menuPanels.AbstractVerticalPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Frame {
    public Frame (){
        super();
        JFrame frame  = new JFrame("Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);


        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 400;
        c.ipadx = 200;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
//For each component to be added to this container:
//...Create the component...
//...Set instance variables in the GridBagConstraints instance...
        pane.add(new LevelPanel(), c);

        JButton button = new JButton();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;
        c.ipadx = 2;
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 1;
        pane.add(button,c);

        frame.add(pane);

        frame.setVisible(true);
    }


}
