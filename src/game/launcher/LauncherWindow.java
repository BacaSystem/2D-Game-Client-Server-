package game.launcher;

import game.data.Player;
import game.window.GameWindow;
import game.window.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LauncherWindow extends JFrame implements ActionListener {
    private JButton online;
    private JButton offline;
    private JTextField ip, port, nick;
    private String ipText, portText, nickText;

    private Player player = Player.getInstance();  //Wywolanie singletona Player, czyli odwołanie się do obiektu player


    public LauncherWindow(){
        //Window setting
        setTitle("Launcher");
        {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setResizable(false);
        }
        //layout setting - buttons, textfields
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));

            add(new JLabel("Nick: ", JLabel.LEFT));
            nick = (JTextField) add(new JTextField(10));

            offline = (JButton) add(new JButton("Offline Game"));
            online = (JButton) add(new JButton("Online Game"));

            add(new JLabel("Adress IP: ", JLabel.LEFT));
            ip = (JTextField) add(new JTextField(10));

            add(new JLabel("Port: ", JLabel.LEFT));
            port = (JTextField) add(new JTextField(10));

            offline.addActionListener(this);
            online.addActionListener(this);
        }
        setVisible(true);
    }

    private void setPlayerNick() {
        var Nick = nick.getText();
        if (!isTextFieldNickEmpty()) {
            player.setNick(Nick);
        }
    }

    private boolean isTextFieldNickEmpty() {
        return nick.getText().trim().isEmpty();
    }

    //Method from ActionListener to listen button actions
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var source = actionEvent.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipText = ip.getText();
                portText = port.getText();

                if(source == online){
                    if(!isTextFieldNickEmpty()) {
                        System.out.println("Online Button");
                        setPlayerNick();
                        System.out.println(player.getNick());
                        dispose();
                        new Menu(false);
                    }

                }
                else if(source == offline){
                    dispose();
                    System.out.println("Offline Button");
                    System.out.println(ipText);
                    System.out.println(portText);
                    //New window - Game
                    new GameWindow();
                 }
            }
        });
    }
}
