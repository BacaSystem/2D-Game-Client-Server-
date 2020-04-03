package game.menuPanels;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import game.Constant.MenuWindowStates;
import game.entities.Button;


/**
 * Klasa zajmujaca się wyświetlaniem widoku Menu głównego
 * Rozszerza klasę JPanel
 */
public class MenuPanel extends JPanel {

    /** Lista nazw przycisków w menu */
    private String[] buttonNames = {MenuWindowStates.NEW_GAME_BUTTON, MenuWindowStates.HIGH_SCORES_BUTTON, MenuWindowStates.HELP_BUTTON, MenuWindowStates.EXIT_BUTTON};
    /** Lista nazw akcji wywolywanych przez pryciski*/
    private String[] buttonActionNames = {MenuWindowStates.NEW_GAME, MenuWindowStates.HIGH_SCORES, MenuWindowStates.HELP, MenuWindowStates.EXIT};
    /** Liczba przycisków */
    private int howManyButtons = buttonActionNames.length;

    /**
     * Konstruktor tworzący komponenty do wyświetlenia w głównym menu
     * @param menuListner Listner nasłuchujący wciśnięcia przycisków
     * Przyciski tworzone są dynamicznie w zależności od dlugości tablicy przycisków
     * Przyciski tworzone są z pomocą klasy Button
     */
    public MenuPanel(ActionListener menuListner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));

        buttonPanel.add(new JLabel("LANDER", SwingConstants.CENTER));
        for (var i = 0; i < howManyButtons; i++) {
            buttonPanel.add(new Button(menuListner, buttonNames[i], buttonActionNames[i]));
        }

        layout.add(buttonPanel);
        panel.add(layout, BorderLayout.CENTER);
        setLayout(new GridLayout(5, 1, 20, 20));
        this.add(panel);

        this.add(panel);
    }
}
