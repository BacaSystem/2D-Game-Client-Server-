package server;

import java.io.*;
import java.net.Socket;

public class ThreadForServer implements Runnable{

    private Socket socket;

    public ThreadForServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (true) {
                InputStream inputStream = socket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);
                String fromClient = br.readLine();
                if(fromClient!=null) {
                    System.out.println("FROM_CLIENT: " + fromClient);

                    String serverMessage = ServerProtocol.serverAction(fromClient);
                    if(serverMessage=="CLOSE_CONNECTION_NOW"){
                        socket.close();
                    }
                    if(serverMessage=="CONNECTION_REJECTED")
                        socket.close();
                    else {
                        pw.println(serverMessage);
                        pw.flush();
                        System.out.println("TO_CLIENT: " + serverMessage);
                        if (serverMessage == "LOG_OUT") {
                            socket.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
