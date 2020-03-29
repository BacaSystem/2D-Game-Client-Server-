package game.window;

import game.Constant.DefaultGameSettings;
import game.Constant.LoadLevel;
import game.Constant.MenuWindowStates;
import game.menuPanels.GamePanel;
import game.menuPanels.HelpPanel;
import game.menuPanels.HighScoresPanel;
import game.menuPanels.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private Dimension size;
    private int defaultWidth = MenuWindowStates.WIDTH;
    private int defaultHeight = MenuWindowStates.HEIGHT;
    private int gameWidth = DefaultGameSettings.WIDTH;
    private int gameHight = DefaultGameSettings.HEIGHT;

    private MenuPanel menuPanel = null;
    private HighScoresPanel highScoresPanel = null;
    private HelpPanel helpPanel = null;
    private GamePanel gamePanel = null;

    public Menu() {
        setWindowSizeAndFocus();
        MakeUI();
        System.out.println("wszedlem w konstruktor MENU");
        this.setResizable(false);
        setVisible(true);
    }

    private void setWindowSizeAndFocus() {
        this.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setWindowSize(false);
         // Centered
        setResizable(true);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        //setLocation(,1);
    }

    private void setWindowSize(boolean game) {
        if (!game) {
            setSize(defaultWidth, defaultHeight); // 1280x720px

        } else {
            setSize(gameWidth, gameHight); // 1280x720px
        }
        setLocationRelativeTo(null);

    }

    private void MakeUI() {
        menuPanel = new MenuPanel( defaultWidth, defaultHeight, this);
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
            case MenuWindowStates.EXIT:
                this.getContentPane().removeAll();
                setPanelsToNull();
                System.exit(0);
                break;

            case MenuWindowStates.MENU:
                this.getContentPane().removeAll();
                setPanelsToNull();
                menuPanel = new MenuPanel(defaultWidth, defaultHeight, this);
                this.add(menuPanel);
                this.revalidate();
                this.repaint();
                this.setResizable(false);
                setWindowSize(false);
                break;


            case MenuWindowStates.NEW_GAME:
                System.out.println("nowa gra");
                this.getContentPane().removeAll();
                setPanelsToNull();

                gamePanel = new GamePanel( gameWidth, gameHight, this);
                this.add(gamePanel);
                gamePanel.setFocusable(true);
                gamePanel.requestFocus();
                gamePanel.requestFocusInWindow();
                setWindowSize(true);
                this.revalidate();
                this.repaint();
                this.setResizable(true);
                break;


            case MenuWindowStates.HIGH_SCORES:
                this.getContentPane().removeAll();
                setPanelsToNull();
                highScoresPanel = new HighScoresPanel( defaultWidth, defaultHeight, this);
                this.add(highScoresPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                this.setResizable(false);
                break;

            case MenuWindowStates.HELP:
                this.getContentPane().removeAll();
                setPanelsToNull();
                helpPanel = new HelpPanel( defaultWidth,defaultHeight, this);
                this.add(helpPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                this.setResizable(false);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

}
