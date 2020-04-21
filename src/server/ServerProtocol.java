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


        String serverMessage;
        switch (serverCommand){

            case "LOGIN":
                serverMessage=login();
                break;
            case "GET_HELP":
                serverMessage=getHelp();
                break;
            case "GET_HELP_LINES":
                serverMessage=getHelpLinesNumber();
                break;

            case "GET_SCOREBOARD_NICKS":
                serverMessage=getScoresNicks();
                break;
            case "GET_SCOREBOARD_SCORES":
                serverMessage=getScoresPoints();
                break;
            case "GET_SCOREBOARD_SIZE":
                serverMessage=getScoreBoardSize();
                break;
            case "SAVE_SCORES":
                saveScores(originalCommand);
                serverMessage="scores Saved";
                break;


            case "LOAD_LEVEL":
                String text[] = originalCommand.split(":");
                serverMessage=loadLevel(Integer.parseInt(text[1]));
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

    private static String getScoresNicks() {
        return GetConfigProperties.getValue("server/scoreBoard", "nicks");
    }

    private static String getScoresPoints() {
        return GetConfigProperties.getValue("server/scoreBoard", "scores");
    }

    private static String getScoreBoardSize() {
        return GetConfigProperties.getValue("server/scoreBoard", "numerOfRecords");
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

    private static String loadLevel(int levelNumber) {
        String command = "";
        int maxLevel = Integer.parseInt(GetConfigProperties.getValue("server/gameSettings","numberOfLevels"));
        if(levelNumber <= maxLevel) {
            String keyNames[] = {"gravitySpeed", "xStart","yStart","xVertecies","yVertecies","xLanding", "yLanding", "numberOfMeteors", "xMeteors", "yMeteors", "speedMeteors", "K","M"};
            for(String key:keyNames) {
                String info = GetConfigProperties.getValue("server/level" + String.valueOf(levelNumber), key);
                if(command=="") {
                    command+=  key + "#" + info;
                } else {
                    command+=  "@" + key + "#" + info;
                }

            }
        }
        return command;
    }

}
