package game.menuPanels;
import configReader.ServerReader;
import game.Constant.MenuWindowStates;
import game.entities.Button;
import configReader.GetConfigProperties;

import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;

/**
 * Klasa zajmujaca się wyświetlaniem widoku Help Menu
 * Rozszerza klasę JPanel
 */
public class HelpPanel extends AbstractVerticalPanel {
    Socket serverSocket;
    /**
     * Konstruktor tworzący komponenty do wyświetlenia w panelu pomocy
     * @param menuListner Listner z menu nasluchujący wciśnięcia przycisków
     */
    public HelpPanel(ActionListener menuListner, Socket server) {
        super();
        serverSocket = server;
        //HERE add components
        super.verticalPanel.add(new JLabel(GetConfigProperties.getValue("helpText", "tytul"), SwingConstants.CENTER));
        //JTextArea help = new JTextArea(getHelp("helptext", "punkt"));
        JTextArea help = new JTextArea(getHelp("helpText", "punkt"));
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
    private String getHelp(String fileName,String key) {
        if(serverSocket!=null) {
            int intLines = Integer.parseInt(ServerReader.getValue(serverSocket, "GET_HELP_LINES"));
            System.out.println(intLines);
            String helpText = ServerReader.getMoreLines(serverSocket,"GET_HELP",intLines);
            System.out.println("help from server");

            return helpText;
        } else {
            System.out.println("localhelp");
            return GetConfigProperties.getValue(fileName, key);
        }


    }
}
