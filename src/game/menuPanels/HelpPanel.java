package game.menuPanels;
import game.Constant.MenuWindowStates;
import game.entities.Button;
import game.data.GetConfigProperties;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

/**
 * Klasa zajmujaca się wyświetlaniem widoku Help Menu
 * Rozszerza klasę JPanel
 */
public class HelpPanel extends JPanel {
    /**
     * Konstruktor tworzący komponenty do wyświetlenia w panelu pomocy
     * @param menuListner Listner z menu nasluchujący wciśnięcia przycisków
     */
    public HelpPanel(ActionListener menuListner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel buttonPanel = new JPanel(new GridLayout(10, 1, 10, 5));

        //HERE add components
        buttonPanel.add(new JLabel(getHelp("helpText", "tytul"), SwingConstants.CENTER));
        JTextArea help = new JTextArea(getHelp("helptext", "punkt"));
        help.setEditable(false);
        buttonPanel.add(help);
        buttonPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        //END OF COMPONENTS
        layout.add(buttonPanel);
        panel.add(layout, BorderLayout.CENTER);
        setLayout(new GridLayout(5, 1, 20, 20));
        this.add(panel);
    }

    /**
     * Metoda pobierająca tekst pomocy
     * @param fileName nazwa pliku pomocy
     * @param key klucz property
     * @return zwraca wartość spod klucza
     */
    private String getHelp(String fileName, String key) {
        return GetConfigProperties.getValue(fileName, key);
    }
}
