package server;
import server.ConfigReader.ConfigReader;

public class ServerProtocol {
    private static boolean acceptingClients= true;
    private static int clientNumber =0;


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
                String str1[] = originalCommand.split(":");
                String str2[] = str1[1].split("@");
                filename = str2[0];
                keys = new String[]{str2[1]};
                serverMessage=getCodedContent(filename,keys);
                break;

            case "PUT":
                String trash[] = originalCommand.split(":");
                String putData[] = trash[1].split("@");
                filename = putData[0];
                String key = putData[1];
                String data = putData[2];
                serverMessage=saveDecodedValue(filename,key,data);
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
                String[] trassh = command.split(":");   //trassh[0] -> command, trassh[1] -> data
                String[] scoreBoardData = trassh[1].split("@"); //-> scoreBoardData[0] -> nicks, scoreBoardData[1] -> scores
                filename = "scoreBoard";
                saveDecodedValue(filename,"nicks",scoreBoardData[0]);
                saveDecodedValue(filename,"scores",scoreBoardData[1]);
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
            serverMessage="LOG_IN client "+clientNumber+"\n";
        } else {
            serverMessage="CONNECTION_REJECTED";
        }
        return serverMessage;
    }


    private static String logout(){
        String serverMessage = "LOGGED OUT " + clientNumber + "\n";
        clientNumber--;
        return "LOGOUT";
    }
    private static String connectionClosed(){
        return "CLOSE_CONNECTION_NOW";
    }


    private static String saveDecodedValue(String filename, String key, String data) {
        ConfigReader.setValue(filename,key,data);
        return "value " + data +" saved in file " + filename + " as " + key + "\n";
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

}
