package game.window;

import game.Constant.DefaultGameSettings;
import game.Constant.LoadLevel;
import game.Constant.MenuWindowStates;
import game.data.GetConfigProperties;
import game.menuPanels.GamePanel;
import game.menuPanels.HelpPanel;
import game.menuPanels.HighScoresPanel;
import game.menuPanels.MenuPanel;

import game.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class Menu extends JFrame implements ActionListener {

    private int defaultWidth = MenuWindowStates.WIDTH;
    private int defaultHeight = MenuWindowStates.HEIGHT;
    private int gameWidth = DefaultGameSettings.WIDTH;
    private int gameHight = DefaultGameSettings.HEIGHT;

    private MenuPanel menuPanel = null;
    private HighScoresPanel highScoresPanel = null;
    private HelpPanel helpPanel = null;
    //private GamePanel gamePanel = null;
    private GameWindow gameWindow = null;

    public Menu() {
        setWindowSizeAndFocus();
        MakeUI();
    }

    private void setWindowSizeAndFocus() {

        this.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Menu");
        setWindowSize(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setVisible(true);

    }

    private void setWindowSize(boolean game) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        if (!game) {
            setSize(defaultWidth, defaultHeight); // 1280x720px
            //setLocation((dim.width-defaultWidth)/2,(dim.height-defaultHeight)/2);
            this.setResizable(false);
        } else {
            setSize(gameWidth, gameHight); // 1280x720px
            this.setResizable(true);
            //setLocation((dim.width-gameWidth)/2,(dim.height-gameHight)/2);
        }
    }

    private void MakeUI() {
        menuPanel = new MenuPanel( defaultWidth, defaultHeight, this);
        this.add(menuPanel);
    }

    private void removeAllPanels() {
        this.getContentPane().removeAll();
        /*if (gamePanel != null) {
            //  gamePanel.stopTheGame();
            gamePanel = null;


        }*/ if (menuPanel != null) {
            menuPanel = null;
        } if (highScoresPanel != null) {
            highScoresPanel = null;
        } if (helpPanel != null) {
            helpPanel = null;
        }
    }

    private void setPanelOptions(boolean game, JPanel panel) {
        this.add(panel);
        if(game) {
            panel.setFocusable(true);
            panel.requestFocus();
            panel.requestFocusInWindow();
        }
        setWindowSize(game);
        this.revalidate();
        this.repaint();
    }




    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch (action) {
            case MenuWindowStates.EXIT:
                removeAllPanels();
                System.exit(0);
                break;

            case MenuWindowStates.MENU:
                removeAllPanels();
                menuPanel = new MenuPanel(defaultWidth, defaultHeight, this);
                setPanelOptions(false,menuPanel);
                break;


            case MenuWindowStates.NEW_GAME:
                removeAllPanels();
                gameWindow = new GameWindow();
                dispose();
                //setPanelOptions(true,gamePanel);
                break;

            case MenuWindowStates.HIGH_SCORES:
                removeAllPanels();
                highScoresPanel = new HighScoresPanel( defaultWidth, defaultHeight, this);
                setPanelOptions(false,highScoresPanel);
                break;

            case MenuWindowStates.HELP:
                removeAllPanels();
                helpPanel = new HelpPanel( defaultWidth,defaultHeight, this);
                setPanelOptions(false,helpPanel);
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

}
