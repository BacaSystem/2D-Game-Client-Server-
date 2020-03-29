package game.launcher;

import game.Constant.LauncherConst;
import game.data.Player;
import game.window.GameWindow;
import game.window.Menu;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.text.BadLocationException;

public class LauncherWindow extends JFrame implements ActionListener {
    private JButton online;
    private JButton offline;
    private JTextField ip, port, nick;
    private String ipText, portText;

    private Player player = Player.getInstance();  //Wywolanie singletona Player, czyli odwołanie się do obiektu player

    public LauncherWindow(){
        //Window setting
        setTitle(LauncherConst.LAUNCHER_TITLE);
        {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(LauncherConst.WIDTH, LauncherConst.HEIGHT);
            setLocationRelativeTo(null);
            setResizable(false);
        }
        //layout setting - buttons, textfields
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));



            add(new JLabel(LauncherConst.NICK_LABEL, JLabel.LEFT));
            nick = (JTextField) add(new JTextField(10));


            offline = (JButton) add(new JButton(LauncherConst.ONLINE_LABEL));
            offline.setEnabled(false);
            online = (JButton) add(new JButton(LauncherConst.OFFLINE_LABEL));
            online.setEnabled(false);

            add(new JLabel(LauncherConst.IP_LABEL, JLabel.LEFT));
            ip = (JTextField) add(new JTextField(10));

            add(new JLabel(LauncherConst.PORT_LABEL, JLabel.LEFT));
            port = (JTextField) add(new JTextField(10));

            offline.addActionListener(this);
            online.addActionListener(this);

            nick.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent documentEvent) {
                    changed(documentEvent);
                }

                @Override
                public void removeUpdate(DocumentEvent documentEvent) {
                    changed(documentEvent);
                }

                @Override
                public void changedUpdate(DocumentEvent documentEvent) {
                    changed(documentEvent);
                }
                public void changed(DocumentEvent documentEvent) {
                    String currText = nick.getText();

                    if (currText.trim().isEmpty()) {
                        online.setEnabled(false);
                        offline.setEnabled(false);
                    } else {
                        online.setEnabled(true);
                        offline.setEnabled(true);
                    }
                }
            });

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
                        new Menu();
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
