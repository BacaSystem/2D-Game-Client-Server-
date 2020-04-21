package server;
import server.ConfigReader.ConfigReader;

import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private static String filename = "port";
    private int port;

    public void runServer() {
        try {
            port = Integer.parseInt(ConfigReader.getValue(filename,"port"));
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is working now");
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ThreadForServer(socket)).start();

            }
        } catch (Exception e){
            System.out.println("Sorry, server not working");
            System.out.println(e);
        }
    }


}
