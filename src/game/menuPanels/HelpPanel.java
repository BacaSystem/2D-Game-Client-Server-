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
    /** Nazwa pliku, w której należy szukać lokalnego tekstu pomocy */
    private final String fileName = "helpText";
    /**
     * Konstruktor tworzący komponenty do wyświetlenia w panelu pomocy
     * @param menuListner Listner z menu nasluchujący wciśnięcia przycisków
     */
    public HelpPanel(ActionListener menuListner, Socket serverSocket) {
        super();
        getHelp(fileName, serverSocket, super.verticalPanel);
        super.verticalPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));
    }

    /**
     * Metoda tworząca widok pomocy
     * @param fileName nazwa pliku pomocy
     * @param serverSocket Socket serweru. W przypadku wersji online może być null.
     * @param  panel JPanel vertykalny, do którego dodany zostanie widok
     */
    private void getHelp(String fileName, Socket serverSocket, JPanel panel) {
        String text = "";
        JTextArea textArea = null;
        JLabel titleLabel = null;
        if(ServerStatus.isConnected()) {
            try {
                Map<String,String> data = ServerConnectivity.getDecodedDataInMap(serverSocket,"GET_HELPTEXT");
                Map<String,String> numberOfLines = ServerConnectivity.getDecodedDataInMap(serverSocket,"GET:help@lines");
                Map<String,String> title = ServerConnectivity.getDecodedDataInMap(serverSocket,"GET:help@title");
                for(int i=1; i<=Integer.parseInt(numberOfLines.get("lines")); i++) {
                    text+= data.get(String.valueOf(i));
                    if(i<Integer.parseInt(numberOfLines.get("lines"))) {
                        text+="\n";
                    }
                }
                System.out.println("online help\n");

                titleLabel = new JLabel(title.get("title"), SwingConstants.CENTER);
                textArea = new JTextArea(text);

            } catch(Exception e) {
                ServerStatus.connectionLost(serverSocket);
            }
        }

        if(!ServerStatus.isConnected()) {
            text = "";
            Map<String,String> data = new HashMap<>();
            String title = ConfigReader.getValue(fileName, "title");
            int numberOfLines = Integer.parseInt(ConfigReader.getValue(fileName, "lines"));
            for(int i=1; i <= numberOfLines; i++) {
                data.put(String.valueOf(i), ConfigReader.getValue(fileName,String.valueOf(i)));
                text+= data.get(String.valueOf(i));
                if(i<numberOfLines) {
                    text+="\n";
                }
            }
            System.out.println("offline help\n");

            titleLabel = new JLabel(title, SwingConstants.CENTER);
            textArea = new JTextArea(text);
        }

        panel.add(titleLabel);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        panel.add(textArea);
    }
}
