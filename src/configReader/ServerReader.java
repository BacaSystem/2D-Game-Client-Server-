package configReader;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerReader {
    public static String talkWithServer(Socket server, String command) {
        String value = "";
        try {
            OutputStream os = server.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            pw.println(command);
            InputStream is = server.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            value = br.readLine();

        } catch(Exception e) {
            System.out.println("There was a problem with your command to server: " + command);
            System.out.println(e);
        }
        return value;
    }


    public static Map<String,String> getDecodedData(Socket server, String serverCommand) {
        Map<String,String> decodedData = new HashMap<>();
        String serverData = ServerReader.talkWithServer(server, serverCommand);
        String krotka[] = serverData.split("@");
        for(String element: krotka) {
            String prop[] = element.split("#");
            decodedData.put(prop[0],prop[1]);
        }

        return decodedData;
    }
}
