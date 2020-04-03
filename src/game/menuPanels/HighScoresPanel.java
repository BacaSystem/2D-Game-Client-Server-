package game.menuPanels;
import game.data.HighScores;
import game.Constant.MenuWindowStates;
import game.entities.Button;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Klasa zajmujaca się wyświetlaniem widoku Najlepszych wyników
 * Rozszerza klasę JPanel
 */
public class HighScoresPanel extends JPanel {
    /** atrubut przechowywujcy referencję na klasę tablocy najlepszych wyników*/
    private HighScores highScore = HighScores.getInstance();
    /** Najlepsze wyniki gry w formie ArrayList pobierane z klasy HighScores, na początku puste*/
    private ArrayList<HighScores.Record> records;

    /**
     * Konstruktor tworzący komponenty do wyświetlenia w widoku najlepszych wyników
     * Lista rekordow jest dynamiczna, zależna od liczby rekordów załadowanej do instancji HighScore
     * @param menuListner Listner nasłuchujący wciśnięcia przycisków
     */
    public HighScoresPanel(ActionListener menuListner) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(5, 6, 5, 6));

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel verticalPanel = new JPanel(new GridLayout(10, 1, 10, 1));

        getData();

        //HERE add components
        for(int i=0; i<highScore.getNumberOfRecords(); i++) {
            JPanel recordPanel = new JPanel();
            recordPanel.add(new JLabel((i+1) + "."));
            recordPanel.add(new JLabel(records.get(i).getNick()));
            recordPanel.add(new JLabel(String.valueOf(records.get(i).getScore()),SwingConstants.RIGHT));
            verticalPanel.add(recordPanel);
        }

        verticalPanel.add(new Button(menuListner, MenuWindowStates.MENU_BUTTON, MenuWindowStates.MENU));

        //END OF COMPONENTS

        layout.add(verticalPanel);
        panel.add(layout, BorderLayout.CENTER);
        setLayout(new GridLayout(5, 1, 20, 20));
        this.add(panel);
        
    }

    /** Metoda pobierające najlepsze wyniki*/
    void getData() {
        highScore.downloadData();
        records = highScore.getRecords();
    }
}
