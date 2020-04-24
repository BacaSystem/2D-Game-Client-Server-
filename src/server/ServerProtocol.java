package server;
import server.ConfigReader.ConfigReader;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ServerProtocol {
    private static boolean acceptingClients= true;
    private static int clientNumber =0;


    public static String serverAction(String command){
        String serverCommand = command;
        String originalCommand= command;
        System.out.println(command);
        if(command.contains("SAVE_SCORES:")){
            originalCommand=command;
            serverCommand="SAVE_SCORES";
        }
        if(command.contains("LOAD_LEVEL:")) {
            originalCommand=command;
            serverCommand="LOAD_LEVEL";
        }
        if(command.contains("GET:")) {
            originalCommand=command;
            serverCommand="GET";
        }
        if(command.contains("PUT:")) {
            originalCommand=command;
            serverCommand = "PUT";
        }

        String filename;
        String[] keys;
        String serverMessage;
        switch (serverCommand){
            case "GAME_SETTINGS":
                keys = new String[]{"width", "height", "lifes", "numberOfLevels", "fuelLevel", "maxLandingSpeed", "speedAccelerating", "startSpeedX", "startSpeedY", "S"};
                filename = "gameSettings";
                serverMessage=getCodedContent(filename,keys);
                break;

            case "GET_GRAPHICS":
                keys = new String[]{"ship", "fireUp", "fireDown", "fireLeft", "fireRight", "gameOver", "menuText", "wonText", "landed", "crashed", "destroyed", "paused", "meteor"};
                filename = "gameGraphics";
                serverMessage=getCodedContent(filename,keys);
                break;

            case "GET_MENU_SETTINGS":
                keys = new String[]{"gameTitle", "newGame", "help", "highScores", "exit", "backToMain", "width", "height","buttonWidth","buttonHeight"};
                filename = "menu";
                serverMessage=getCodedContent(filename,keys);
                break;

            case "LOAD_LEVEL":
                String text[] = originalCommand.split(":");
                keys = new String[]{"gravitySpeed", "xStart","yStart","xVertecies","yVertecies","xLanding", "yLanding", "numberOfMeteors", "xMeteors", "yMeteors", "speedMeteors", "K","M"};
                filename = "level" + text[1];
                serverMessage=getCodedContent(filename,keys);
                break;

            case "GET_HELPTEXT":
                keys = new String[]{"text1","text2","text3"};
                filename = "help";
                serverMessage=getCodedContent(filename,keys);
                break;

            case "GET":
                filename = getFileNameFormCommand(originalCommand);
                keys = getKeysFromCommand(originalCommand);
                serverMessage=getCodedContent(filename,keys);
                break;

            case "PUT":
                filename = getFileNameFormCommand(originalCommand);
                String data = removeFileNameFromCommand(originalCommand);
                serverMessage=saveDecodedValue(filename,data);
                break;

            case "LOGIN":
                serverMessage=login();
                break;

            case "GET_SCOREBOARD":
                filename = "scoreBoard";
                keys = new String[]{"nicks","scores"};
                serverMessage=getCodedContent(filename,keys);
                break;


            case "SAVE_SCORES":
                filename = "scoreBoard";
                saveDecodedValue(filename, originalCommand);
                serverMessage="Scores Saved" + "\n";
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


    private static String login(){
        String serverMessage;
        if(acceptingClients) {
            clientNumber++;
            serverMessage="LOG_IN_"+clientNumber+"\n";
        } else {
            serverMessage="CONNECTION_REJECTED";
        }
        return serverMessage;
    }


    private static String logout(){
        //String serverMessage = "LOGGED OUT " + clientNumber + "\n";
        clientNumber--;
        return "LOGOUT";
    }
    private static String connectionClosed(){
        return "CLOSE_CONNECTION_NOW";
    }


    private static String saveDecodedValue(String filename, String command) {
        String[] trash = command.split(":");    // -> trahs[0] smieci, trash[1] -> dobry text
        String[] newData = trash[1].split("@"); // -> newData[0] -> lista nickow, newData[1] -> lista scorow
        for(String bytes: newData) {
            String[] finalData = bytes.split("#");
            ConfigReader.setValue(filename, finalData[0],finalData[1]);
        }
        return ":  VALUE_SAVED" + "\n";
    }

    private static String getCodedContent(String filename, String[] keys) {
        StringBuilder command = new StringBuilder();
        String keyNames[] = keys;
        for(String key:keyNames) {
            String info = ConfigReader.getValue(filename, key);
            if(command.toString().equals("")) {
                command.append(key).append("#").append(info);
            } else {
                command.append("@").append(key).append("#").append(info);
            }
        }
        return command.toString() +  "\n";
    }







    //METODY POMOCNICZE



    private static String getFileNameFormCommand(String command) {
        String trash[] = command.split(":");
        String putData[] = trash[1].split("@");
        return putData[0];
    }

    private static String removeFileNameFromCommand(String command) {
        String returnComamand = "";
        String[] trash = command.split(":");
        String[] newTrash = trash[1].split("@");
        for(int i=1; i<newTrash.length; i++) {
            if(returnComamand == "") {
                returnComamand+= trash[0] + ":" + newTrash[i];
            } else {
                returnComamand+= "@" + newTrash[i];
            }

        }
        return returnComamand;
    }

    private static String[] getKeysFromCommand(String command) {
        command = removeFileNameFromCommand(command);
        String[] x = command.split(":");
        String[] xx = x[1].split("@");
        List<String> keys = new ArrayList<>();
        for(String xxx: xx) {
            String[] y = xxx.split("#");
            keys.add(y[0]);
        }
        return keys.toArray(new String[keys.size()]);

    }

}
