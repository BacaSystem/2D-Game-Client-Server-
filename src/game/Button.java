package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(/*int width, int height */ActionListener menuListener, String buttonName, String buttonAction) {
        this.setFocusable(false);
        this.addActionListener(menuListener);
        this.setActionCommand(buttonAction);
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(300,50));
        //this.setPreferredSize(new Dimension(width, height));
    }
}
