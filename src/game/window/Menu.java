package game.window;

import game.Constant.DefaultGameSettings;
import game.Constant.LoadLevel;
import game.Constant.MenuWindowStates;
import game.data.GetConfigProperties;
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
            setLocation((dim.width-defaultWidth)/2,(dim.height-defaultHeight)/2);
            this.setResizable(false);
        } else {
            setSize(gameWidth, gameHight); // 1280x720px
            this.setResizable(true);
            setLocation((dim.width-gameWidth)/2,(dim.height-gameHight)/2);
        }



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
                setWindowSize(false);
                //GetConfigProperties.setValue("highScoreTable","2");
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
                break;


            case MenuWindowStates.HIGH_SCORES:
                this.getContentPane().removeAll();
                setPanelsToNull();
                highScoresPanel = new HighScoresPanel( defaultWidth, defaultHeight, this);
                this.add(highScoresPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;

            case MenuWindowStates.HELP:
                this.getContentPane().removeAll();
                setPanelsToNull();
                helpPanel = new HelpPanel( defaultWidth,defaultHeight, this);
                this.add(helpPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();

                break;

            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
    }

}
