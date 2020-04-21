package server;

import configReader.GetConfigProperties;

import java.io.BufferedReader;
import java.io.FileReader;

public class ServerProtocol {
    private static boolean acceptingClients= true;
    private static int clientNumber =1;


    public static String serverAction(String command){
        String serverCommand = command;
        String originalCommand= command;
        System.out.println(command);
        if(command.contains("GET_LEVEL:")){
            originalCommand=command;
            serverCommand=("GET_LEVEL");
        }
        if(command.contains("SAVE_SCORES:")){
            originalCommand=command;
            serverCommand="SAVE_SCORES";
        }
        if(command.contains("LOAD_LEVEL:")) {
            originalCommand=command;
            serverCommand="LOAD_LEVEL";
        }

        String filename;
        String[] keys;
        String serverMessage;
        switch (serverCommand){
            case "GAME_SETTINGS":
                keys = new String[]{"width", "height", "lifes", "numberOfLevels", "fuelLevel", "maxLandingSpeed", "speedAccelerating", "startSpeedX", "startSpeedY", "S"};
                filename = "server/gameSettings";
                serverMessage=getCodedContent(filename,keys);
                break;
            case "GET_GRAPHICS":
                keys = new String[]{"ship", "fireUp", "fireDown", "fireLeft", "fireRight", "gameOver", "menuText", "wonText", "landed", "crashed", "destroyed", "paused", "meteor"};
                filename = "server/gameGraphics";
                serverMessage=getCodedContent(filename,keys);
                break;
            case "GET_MENU_SETTINGS":
                keys = new String[]{"gameTitle", "newGame", "help", "highScores", "exit", "backToMain", "width", "height","buttonWidth","buttonHeight"};
                filename = "server/menu";
                serverMessage=getCodedContent(filename,keys);
                break;
            case "LOAD_LEVEL":
                String text[] = originalCommand.split(":");
                keys = new String[]{"gravitySpeed", "xStart","yStart","xVertecies","yVertecies","xLanding", "yLanding", "numberOfMeteors", "xMeteors", "yMeteors", "speedMeteors", "K","M"};
                filename = "server/level" + text[1];
                serverMessage=getCodedContent(filename,keys);
                break;

            case "LOGIN":
                serverMessage=login();
                break;
            case "GET_HELP":
                serverMessage=getHelp();
                break;
            case "GET_HELP_LINES":
                serverMessage=getHelpLinesNumber();
                break;

            case "GET_SCOREBOARD":
                serverMessage=getScoteBoard();
                break;


            case "SAVE_SCORES":
                saveScores(originalCommand);
                serverMessage="scores Saved";
                break;

            case "LOGOUT":
                serverMessage=logout();
                break;
            case "CONNECTION_CLOSED":
                serverMessage=connectionClosed();
                break;
            default:
                serverMessage="INVALID_COMMAND";
        }
        return serverMessage;
    }

    private static String getHelp(){
        return GetConfigProperties.getValue("server/help","text");
    }
    private static String getHelpLinesNumber() {
        return GetConfigProperties.getValue("server/help","lines");
    }

    private static String login(){
        String serverMessage;
        if(acceptingClients) {
            serverMessage="LOG_IN "+clientNumber+"\n";
            clientNumber++;
        } else {
            serverMessage="CONNECTION_REJECTED";
        }
        return serverMessage;
    }


    private static String logout(){
        return "LOG_OUT";
    }
    private static String connectionClosed(){
        return "CLOSE_CONNECTION_NOW";
    }

    private static String getScoteBoard() {
        String ScoreBoardSize = GetConfigProperties.getValue("server/scoreBoard", "numerOfRecords");
        String ScoreBoardNicks = GetConfigProperties.getValue("server/ScoreBoard", "nicks");
        String ScoreBoardScore = GetConfigProperties.getValue("server/ScoreBoard", "scores");
        String command = ScoreBoardSize + "@" + ScoreBoardNicks + "#" + ScoreBoardScore;
        return command;

    }

    private static void saveScores(String text) {
        String text1[] = text.split(":");
        // text1[0] to komenda, text2[1] to nicki i wyniki
        String text2[] = text1[1].split("@");
        //text2[0] to nicki, text2[1] to wyniki
        System.out.println(text2[0]);
        System.out.println(text2[1]);
        GetConfigProperties.setValue("server/scoreBoard", "nicks", text2[0]);
        GetConfigProperties.setValue("server/scoreBoard", "scores", text2[1]);
    }


    private static String getCodedContent(String filename, String[] keys) {
        StringBuilder command = new StringBuilder();
        String keyNames[] = keys;
        for(String key:keyNames) {
            String info = GetConfigProperties.getValue(filename, key);
            if(command.toString().equals("")) {
                command.append(key).append("#").append(info);
            } else {
                command.append("@").append(key).append("#").append(info);
            }
        }
        return command.toString();
    }

}
