package game.menuPanels;
import game.Constant.MenuWindowStates;
import game.entities.Button;
import game.data.GetConfigProperties;

import javax.swing.*;
import java.awt.event.*;

/**
 * Klasa zajmujaca się wyświetlaniem widoku Help Menu
 * Rozszerza klasę JPanel
 */
public class HelpPanel extends AbstractVerticalPanel {
    /**
     * Konstruktor tworzący komponenty do wyświetlenia w panelu pomocy
     * @param menuListner Listner z menu nasluchujący wciśnięcia przycisków
     */
    public HelpPanel(ActionListener menuListner) {
        super();
        //HERE add components
        super.verticalPanel.add(new JLabel(getHelp("helpText", "tytul"), SwingConstants.CENTER));
        JTextArea help = new JTextArea(getHelp("helptext", "punkt"));
        help.setEditable(false);
        super.verticalPanel.add(help);
        super.verticalPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        //END OF COMPONENTS

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
