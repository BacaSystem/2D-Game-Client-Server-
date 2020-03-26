package game.menu;

import game.data.Player;

import javax.swing.*;
import java.awt.event.ActionListener;

public class NickMenu extends JPanel {
    private JButton play;
    private JButton back;
    private JTextField nick;
    private String nickText;
    private Player player = Player.getInstance();  //Wywolanie singletona Player, czyli odwołanie się do obiektu player

    public NickMenu(ActionListener menuListner) {

        add(new JLabel("Your nick: ", JLabel.LEFT));
        nick = (JTextField) add(new JTextField(10));

        add(backToMenuButton(menuListner));
        add(startGameButton(menuListner));
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