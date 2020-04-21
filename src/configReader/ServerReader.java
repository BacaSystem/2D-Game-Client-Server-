package configReader;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;

public class ServerReader {
    public static String getValue(Socket server, String command) {
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

    public static String getMoreLines(Socket server, String command, int numberOfLines) {
        String value = "";

        try {
            OutputStream os = server.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            pw.println(command);
            InputStream is = server.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for(int i=0; i< numberOfLines; i++) {
                String line = br.readLine();
                value = value + line + "\n";
            }

        } catch(Exception e) {
            System.out.println("There was a problem with your command to server: " + command);
            System.out.println(e);
        }

        return value;
    }


}
