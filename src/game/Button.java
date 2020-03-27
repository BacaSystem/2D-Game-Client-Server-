package game;

import javax.swing.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(ActionListener menuListner, String buttonName, String buttonAction) {
        this.setFocusable(false);
        this.addActionListener(menuListner);
        this.setActionCommand(buttonAction);
        this.setText(buttonName);

    }
}
