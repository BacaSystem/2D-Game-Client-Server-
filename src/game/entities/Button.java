package game.entities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Klasa tworząca jednakowe przyciski
 */
public class Button extends JButton {
    /**
     * @param menuListener Listner do nasłuchiwania przycisku
     * @param buttonName nazwa przycisku
     * @param buttonAction nazwa akcji przycisku
     */
    public Button(/*int width, int height */ActionListener menuListener, String buttonName, String buttonAction) {
        this.setFocusable(false);
        this.addActionListener(menuListener);
        this.setActionCommand(buttonAction);
        this.setText(buttonName);
        this.setPreferredSize(new Dimension(400,50));
        //this.setPreferredSize(new Dimension(width, height));
    }
}
