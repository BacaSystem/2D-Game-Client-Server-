package game.window;

import game.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NickWindow extends JFrame implements ActionListener{
    private JButton play;
    private JButton back;
    private JTextField nick;
    private String nickText;
    private Player player = Player.getInstance();  //Wywolanie singletona Player, czyli odwołanie się do obiektu player

    public NickWindow() {
        this.setFocusable(true);
        this.setTitle("Nick");

        this.setSize(400, 200); // 1280x720px
        this.setLocationRelativeTo(null); // Centered
        this.setResizable(true); // Resizable

        setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Your nick: ", JLabel.LEFT));
        nick = (JTextField) add(new JTextField(10));

        back = (JButton) add(new JButton("Back"));
        play = (JButton) add(new JButton("Play"));


        play.addActionListener(this);
        back.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var source = actionEvent.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                nickText = nick.getText();

                if(source == back){
                    //Troche bałagan, ale testowałem
                    dispose();
                    System.out.println("Back Button");
                    System.out.println("0---------0");
                    System.out.println(player.getNick());
                    player.setNick(nickText);
                    System.out.println(player.getNick());
                    System.out.println("0---------0");
                    new game.Menu(false);
                }
                else if(source == play){
                    //To samo, co powyżej, testy
                    if(!nickText.trim().isEmpty()) {
                        dispose();
                        System.out.println("Play button");
                        //New window - Game
                        //game.player.setNick(nickText);
                        System.out.println(player.getNick());
                        new GameWindow();
                    }
                }
            }
        });
    }
}