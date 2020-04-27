package game.launcher;

import game.configReader.ConfigReader;

import javax.swing.*;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

/**
 * Klasa główna launchera gry. Wywołuje konstruktor okna launchera. Zawiera main()!
 */
public class GameLauncher {

    public GameLauncher(){
        new LauncherWindow();
    }

    public static void main (String[] args) throws IOException {
        String[] agent = ManagementFactory.getRuntimeMXBean().getInputArguments().toArray(new String[0]);
        try {
            if(agent[0].contains("IDEA")) {
                ConfigReader.useIDE();
            }
        } catch(Exception e) {
                ConfigReader.useTerminal();
        }
        /*
        System.out.println("app Start");
        System.out.println("Are you using IDE?\nYES - '1'\nNO - '2'\n");
        Scanner scanner = new Scanner( System. in);
        String inputString = scanner. nextLine();
        System.out.println("your desition: '" + inputString + "'");
        if(inputString.equals("1")) {
            ConfigReader.useIDE();
        } else {
            ConfigReader.useTerminal();
        }
        */
        SwingUtilities.invokeLater(new Runnable() { // Run new Thread
            @Override
            public void run() {
                new GameLauncher();
            }
        });
    }

}
