package game;

import game.Player;
import game.window.GameWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NickMenu extends JPanel {
    private JButton play;
    private JButton back;
    private JTextField nick;
    private String nickText;
    private Player player = Player.getInstance();  //Wywolanie singletona Player, czyli odwołanie się do obiektu player

    public NickMenu(int panelWidth, int panelHeight, ActionListener menuListner) {


        this.setSize(400, 200); // 1280x720px
        //setPreferredSize(400,200);

        add(new JLabel("Your nick: ", JLabel.LEFT));
        nick = (JTextField) add(new JTextField(10));

        //back = (JButton) add(new JButton("Back"));
        add(backToMenuButton(menuListner));
        add(startGameButton(menuListner));
        //play = (JButton) add(new JButton("Play"));

        //back.addActionListener(this);

        setVisible(true);
    }

    private JButton backToMenuButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Back");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("BackToMainMenuFromNickMenu");
        return backToMainMenuButton;
    }

    private JButton startGameButton(ActionListener menuListner) {
        JButton backToMainMenuButton = new JButton("Play");
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(menuListner);
        backToMainMenuButton.setActionCommand("StartGameButton");
        return backToMainMenuButton;
    }

    public String getNick(){
        return nick.getText();
    }

    public boolean isNickEmpty() {
        return nick.getText().trim().isEmpty();
    }

    public void setNickOnPlayer() {
        if (!isNickEmpty()) {
            player.setNick(getNick());
        }

    }

}