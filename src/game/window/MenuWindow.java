package game.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JFrame implements ActionListener {
    JButton newGame;
    JButton highScores;
    JButton help;
    JButton exit;

    public MenuWindow() {
        this.setFocusable(true);
        this.setTitle("Main Menu");
        {
            this.setSize(1280, 720); // 1280x720px
            this.setLocationRelativeTo(null); // Centered
            this.setResizable(true); // Resizable

        }
        {
            setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            newGame = (JButton) add(new JButton("New Game"));
            highScores = (JButton) add(new JButton("Best Scores"));
            help = (JButton) add(new JButton("Help"));
            exit = (JButton) add(new JButton("Exit"));

            newGame.addActionListener(this);
            exit.addActionListener(this);
        }
        setVisible(true);
    }

    //Method from ActionListener to listen button actions
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        var source = actionEvent.getSource();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                if(source == newGame){
                    dispose();
                    System.out.println("New Game");
                    new NickWindow();


                }
                else if(source == exit) {
                    System.exit(0);
                }
                /*else if(source == offline){
                    System.out.println("Offline Button");
                    //New window - Game
                    new GameWindow();
                }*/
            }
        });
    }
}
