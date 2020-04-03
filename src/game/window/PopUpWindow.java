package game.window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import game.GameManager;
import game.controller.KeyHandler;
import game.Game;

/**
 * Klasa tworząca okienko Pop Up po zakończeniu gry z informacją na temat jego wyniku
 * Implementuje dwa przyciski z ActionListenerem
 * Po wciśnięciu odpowiedniego przycisku gracz jest przenoszony do odpowiedniego stanu
 * menuButton - powrót do menu
 * trybutton - ponowne podejście do gry
 */
public class PopUpWindow {
    public  PopUpWindow(GameWindow frame, GameManager manager, KeyHandler key, Game game){
        JFrame popUp = new JFrame();
        {
            popUp.setTitle("");
            popUp.setSize(200,200);
            popUp.setLocationRelativeTo(null);
            popUp.setResizable(false);
            popUp.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            popUp.setLayout(new FlowLayout());
        }
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));
        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));


        if(manager.won) {
            buttonPanel.add(new JLabel("Congratlations, You've won!"), SwingConstants.CENTER);
        } else {
            buttonPanel.add(new JLabel("You've lost!", SwingConstants.CENTER));
        }
        buttonPanel.add(new JLabel("Your Score was: " + manager.scoreOnWinOrLose), SwingConstants.CENTER);
        JButton menuButton = new JButton("Return to menu");
        JButton tryButton = new JButton("Try again");

        buttonPanel.add(menuButton);
        buttonPanel.add(tryButton);


        layout.add(buttonPanel);
        panel.add(layout, BorderLayout.CENTER);
        popUp.add(panel);



        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                key.releaseAll();
                manager.reload();
                popUp.dispose();
                game.hasPopUp = false;
                frame.goToMenu();
            }
        });
        tryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                key.releaseAll();
                manager.reload();
                game.hasPopUp = false;
                popUp.dispose();
            }
        });
        popUp.setVisible(true);

    }
}
