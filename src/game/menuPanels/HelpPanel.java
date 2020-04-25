package game.menuPanels;
import game.serverConnection.ServerStatus;
import game.serverConnection.ServerConnectivity;
import game.Constant.MenuWindowStates;
import game.entities.Button;
import game.configReader.ConfigReader;

import javax.swing.*;
import java.awt.event.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Klasa zajmujaca się wyświetlaniem widoku Help Menu
 * Rozszerza klasę JPanel
 */
public class HelpPanel extends AbstractVerticalPanel {
    /**
     * Konstruktor tworzący komponenty do wyświetlenia w panelu pomocy
     * @param menuListner Listner z menu nasluchujący wciśnięcia przycisków
     */
    public HelpPanel(ActionListener menuListner, Socket server) {
        super();
        //HERE add components
        super.verticalPanel.add(new JLabel(ConfigReader.getValue("helpText", "tytul"), SwingConstants.CENTER));
        //JTextArea help = new JTextArea(getHelp("helptext", "punkt"));
        JTextArea help = new JTextArea(getHelp("helpText", server));
        help.setLineWrap(true);
        help.setWrapStyleWord(true);
        help.setEditable(false);
        super.verticalPanel.add(help);
        super.verticalPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        //END OF COMPONENTS

    }

    /**
     * Metoda pobierająca tekst pomocy
     * @param fileName nazwa pliku pomocy
     * @return zwraca wartość spod klucza
     */
    private String getHelp(String fileName, Socket serverSocket) {
        String text = "";
        if(ServerStatus.isConnected()) {
            try {
                Map<String,String> data = ServerConnectivity.getDecodedDataInMap(serverSocket,"GET_HELPTEXT");
                text+= data.get("text1")+ "\n" +data.get("text2")+ "\n" +data.get("text3");
                System.out.println("online help");
                return text;
            } catch(Exception e) {
                ServerStatus.connectionLost(serverSocket);
            }
        }
        if(!ServerStatus.isConnected()) {
            text = "";
            Map<String,String> data = new HashMap<>();
            data.put("text1", ConfigReader.getValue(fileName,"text1"));
            data.put("text2", ConfigReader.getValue(fileName,"text2"));
            data.put("text3", ConfigReader.getValue(fileName,"text3"));
            text+= data.get("text1")+ "\n" +data.get("text2")+ "\n" +data.get("text3");
            System.out.println("offline help");
            return text;
        }
        return text;
    }
}
