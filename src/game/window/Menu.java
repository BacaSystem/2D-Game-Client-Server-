package game.window;

import game.menuPanels.GamePanel;
import game.menuPanels.HelpPanel;
import game.menuPanels.HighScoresPanel;
import game.menuPanels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    //ComponentListner -> Dzięki temu interfejsowi możemy dokonywać autoskalowania, do ogarnięcia

    private Dimension size;

    private MenuPanel menuPanel = null;
    private HighScoresPanel highScoresPanel = null;
    private HelpPanel helpPanel = null;
    private GamePanel gamePanel = null;

    public Menu() {
        size = new Dimension(1280, 720);
        setWindowSizeAndFocus();
        MakeUI();
        System.out.println("wszedlem w konstruktor MENU");

        setVisible(true);
    }

    private void setWindowSizeAndFocus() {
        this.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setSize(1280, 720); // 1280x720px
        setLocationRelativeTo(null); // Centered
        setResizable(true);
        setLayout(new FlowLayout(FlowLayout.CENTER, 60, 50));
    }

    private void setWindowSize(boolean isNickWindow) {
        if (!isNickWindow) {
            size = new Dimension(1280, 720);
            setSize(1280, 720); // 1280x720px
        }
        else {
            setSize(400, 200); // 1280x720px
        }
    }

    private void MakeUI() {
        menuPanel = new MenuPanel((int) size.getWidth(), (int) size.getHeight(), this);
        this.add(menuPanel);

    }

    void setPanelsToNull() {
        if (gamePanel != null) {
            gamePanel.stopTheGame();
            gamePanel = null;
        } if (menuPanel != null) {
            menuPanel = null;
        } if (highScoresPanel != null) {
            highScoresPanel = null;
        } if (helpPanel != null) {
            helpPanel = null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("eloPozniej");
        String action = actionEvent.getActionCommand();
        switch (action) {
            case "Exit":
                this.getContentPane().removeAll();
                setPanelsToNull();
                System.exit(0);
                break;

            case "MainMenu":
                this.getContentPane().removeAll();
                setPanelsToNull();
                menuPanel = new MenuPanel((int) size.getWidth(), (int) size.getHeight(), this);
                this.add(menuPanel);
                this.revalidate();
                this.repaint();
                break;


            case "NewGame":
                System.out.println("nowa gra");
                this.getContentPane().removeAll();
                setPanelsToNull();

                gamePanel = new GamePanel((int) size.getWidth(), (int) size.getHeight(), this);
                this.add(gamePanel);
                gamePanel.setFocusable(true);
                gamePanel.requestFocus();
                gamePanel.requestFocusInWindow();
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;


            case "HighScores":
                this.getContentPane().removeAll();
                setPanelsToNull();
                highScoresPanel = new HighScoresPanel((int) size.getWidth(), (int) size.getHeight(), this);
                this.add(highScoresPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;

            case "Help":
                this.getContentPane().removeAll();
                setPanelsToNull();
                helpPanel = new HelpPanel((int) size.getWidth(), (int) size.getHeight(), this);
                this.add(helpPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;

        }
    }

}
