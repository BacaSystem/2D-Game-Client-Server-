package game;

import game.window.NickWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame implements ActionListener {

    private Dimension size;

    private JPanel menuPanel;

    private JLabel GameNameLabel;

    private JButton newGameButton;
    private JButton highScoresButton;
    private JButton helpButton;
    private JButton exitButton;

    private HighScores highScoresPanel;
    private HelpMenu helpPanel;
    private NickMenu nickPanel;
    private Game_ gamePanel;

    private boolean isGameView;

    public Menu(boolean game) {
        size = new Dimension(1280, 720);
        setWindowSizeAndFocus();
        MakeUI();
        System.out.println("wszedlem w konstruktor MENU");

        if (game == true) {
            this.isGameView = true;
        }
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
        menuPanel = new JPanel();
        GameNameLabel = new JLabel("LANDER", SwingConstants.CENTER);

        newGameButton = new JButton("New Game");
        newGameButton.setActionCommand("NewGame");
        newGameButton.addActionListener(this);
        newGameButton.setFocusable(false);

        highScoresButton = new JButton("HighScores");
        highScoresButton.setActionCommand("HighScores");
        highScoresButton.addActionListener(this);
        newGameButton.setFocusable(false);

        helpButton = new JButton("Help");
        helpButton.setActionCommand("Help");
        helpButton.addActionListener(this);
        helpButton.setFocusable(false);

        exitButton = new JButton("Exit");
        exitButton.setActionCommand("Exit");
        exitButton.addActionListener(this);
        exitButton.setFocusable(false);

        menuPanel.add(GameNameLabel);
        menuPanel.add(newGameButton);
        menuPanel.add(highScoresButton);
        menuPanel.add(helpButton);
        menuPanel.add(exitButton);
        getContentPane().add(menuPanel);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String action = actionEvent.getActionCommand();
        switch (action) {
            case "Exit":
                System.exit(0);
                break;

            case "NewGame":
                nickPanel = new NickMenu(this);
                this.remove(menuPanel);
                this.add(nickPanel);
                this.revalidate();
                setWindowSize(true);
                this.repaint();
                break;

            case "StartGameButton":
                if (!nickPanel.isNickEmpty())
                {
                    nickPanel.setNickOnPlayer();
                    gamePanel = new Game_((int) size.getWidth(), (int) size.getHeight(), this);

                    this.remove(nickPanel);
                    this.add(gamePanel);
                    gamePanel.setFocusable(true);
                    gamePanel.requestFocus();
                    gamePanel.requestFocusInWindow();
                    setWindowSize(false);
                    this.revalidate();
                    this.repaint();
                }
                break;


            case "HighScores":
                highScoresPanel = new HighScores((int) size.getWidth(), (int) size.getHeight(), this);
                this.remove(menuPanel);
                this.add(highScoresPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;

            case "Help":
                helpPanel = new HelpMenu((int) size.getWidth(), (int) size.getHeight(), this);
                this.remove(menuPanel);
                this.add(helpPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;



            case "BackToMainMenuFromHighScoresPanel":
                this.remove(highScoresPanel);
                highScoresPanel = null;
                this.add(menuPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;


            case "BackToMainMenuFromHelpPanel":
                this.remove(helpPanel);
                helpPanel = null;
                this.add(menuPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;
            case "BackToMainMenuFromGamePanel":
                this.remove(gamePanel);
                gamePanel.stopTheGame();
                gamePanel = null;
                this.add(menuPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;

            case "BackToMainMenuFromNickMenu":
                this.remove(nickPanel);
                nickPanel = null;
                this.add(menuPanel);
                setWindowSize(false);
                this.revalidate();
                this.repaint();
                break;
        }
    }


}
