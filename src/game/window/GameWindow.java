package game.window;

import game.Constant.DefaultGameSettings;
import game.Constant.MenuWindowStates;
import game.data.GetConfigProperties;
import game.menuPanels.HelpPanel;
import game.menuPanels.HighScoresPanel;
import game.menuPanels.MenuPanel;

import game.Game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame implements ActionListener{

    private int defaultWidth = MenuWindowStates.WIDTH;
    private int defaultHeight = MenuWindowStates.HEIGHT;
    private int gameWidth = DefaultGameSettings.WIDTH;
    private int gameHight = DefaultGameSettings.HEIGHT;

    private MenuPanel menuPanel = null;
    private HighScoresPanel highScoresPanel = null;
    private HelpPanel helpPanel = null;
    private Game gamePanel = null;

    public GameWindow() {
        setWindowSizeAndFocus();
        MakeUI();
    }

    private void setWindowSizeAndFocus() {

        this.setFocusable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Lunar Lander");
        setWindowSize(false);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private void setWindowSize(boolean game) {

        if (!game) {
            setSize(defaultWidth, defaultHeight);
            this.setResizable(false);
        } else {
            setSize(gameWidth, gameHight);
            this.setResizable(true);
            setLocationRelativeTo(null);
        }
    }

    private void MakeUI() {
        menuPanel = new MenuPanel( defaultWidth, defaultHeight, this);
        this.add(menuPanel);
    }

    private void removeAllPanels() {
        this.getContentPane().removeAll();
         if (menuPanel != null) {
            menuPanel = null;
        } if (highScoresPanel != null) {
            highScoresPanel = null;
        } if (helpPanel != null) {
            helpPanel = null;
        } if (gamePanel != null){
            gamePanel.running = false;
            gamePanel = null;
            setSize(defaultWidth,defaultHeight);
            setLocationRelativeTo(null);
        }
    }

    private void setPanelOptions(boolean game, JPanel panel) {
        setWindowSize(game);
        if(game)
            setLayout(new BorderLayout());
        else
            setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        this.add(panel);
        if(game) {
            panel.setFocusable(true);
            panel.requestFocus();
            panel.requestFocusInWindow();
        }
        this.revalidate();
        this.repaint();
    }

    public void goToMenu(){
        removeAllPanels();
        menuPanel = new MenuPanel(defaultWidth, defaultHeight, this);
        setPanelOptions(false, menuPanel);
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
                GetConfigProperties.setValue("highScores");
                break;


            case MenuWindowStates.NEW_GAME:
                removeAllPanels();
                //gameWindow = new GameWindow();
                //dispose();
                gamePanel = new Game(gameWidth, gameHight, this);
                setPanelOptions(true,gamePanel);
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
