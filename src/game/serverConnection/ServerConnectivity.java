package game.serverConnection;

import game.configReader.ConfigReader;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Klasa odpowiadająca za kontakt z serwerem
 */
public class ServerConnectivity {

    /**
     * Metoda wysyłająca żądane klienta do serwera i zwracająca odpowiedź serwera
     * @param server Socket serwera
     * @param command Ządanie użytkownika
     * @return Odpowiedź serwera w nieprzetworzonej formie
     */
    public static String talkWithServer(Socket server, String command) {
        String value = "";
        try {
            OutputStream os = server.getOutputStream();
            PrintWriter pw = new PrintWriter(os,true);
            pw.println(command);
            InputStream is = server.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            value = br.readLine();
            //System.out.println(value);

        } catch(Exception e) {
            Logger.getLogger(ServerConnectivity.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("There was a problem with your command to server: " + command +  "\n");
        }
        if(value == "INVALID_COMMAND" || value =="FATAL_ERROR") {
            if(value =="FATAL_ERROR") {
                System.out.println("FATAL_ERROR");
            } else {
                System.out.println("INVALID_COMMAND");
            }
            ServerStatus.wrongCommand(server,command, value);

            /*TODO ->
                       Co, jeśli nie udalo się pobrać informacji z serwera, lub wprowadzono błędną komendę?
                       Proponuję np. przerwać połączenie z srwerem (Bo w niektórych sytuacjach, np. przy późniejszej
                       próbie przypisania jakiegoś inta pustym/nieprawidłowym Stringiem program może się posypać).

            FIXME ->
                     Proponuję np. wywolać zmodyfikowaną metodę connectionLost z klasy ServerStatus.
                     Pozwoli to zamknać połączenie sieciowe i dokonanie przez użytkownika decyzji,
                     czy chce grać dalej offline, czy woli przerwać działanie aplikacji.
                     Oczywiście w tym przypadku przydałoby się poinformować użytkownika o napotkanym probielmie w komunikacie,
                     więc należy tę metodę trochę zmodyfikować
             */
        }
        return value;
    }


    /**
     * Metoda wysyłająca żądanie klienta do serwera, odbierająca odpowiedź i przetwarzajaca jego odpowiedź
     * na Hash Mapę par klucz - wartość klucza
     * @param server Socket serwera
     * @param serverCommand Ządanie klienta
     * @return Przetworzona Mapa par klucz-wartość
     */
    public static Map<String,String> getDecodedDataInMap(Socket server, String serverCommand) {
        Map<String,String> decodedData = new HashMap<>();
        String serverData = ServerConnectivity.talkWithServer(server, serverCommand);
        String krotka[] = serverData.split("@");
        for(String element: krotka) {
            String prop[] = element.split("#");
            decodedData.put(prop[0],prop[1]);
        }
        return decodedData;
    }

    /**
     * Metoda próbująca połączyć sie z serwerem o zadanym adresie IP i porcie
     * @param IPAddress adres IP, na którym szukany jest serwer
     * @param port Port, na którym szukany jest serwer
     * @return Jeśli próba połączenia zakończyła się powodzeniem, zwraca Socket do serwera.
     *          W przeciwnym przypadku zwraca null.
     */
    public static Socket connectToServer(String IPAddress, int port) {
        try {
            BufferedReader br;
            //String IPAddress="localhost";
            //int port=4010;
            Socket serverSocket = new Socket(IPAddress, port);
            OutputStream os = serverSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println("LOGIN");
            InputStream is = serverSocket.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            if(br.readLine().contains("LOG_IN")){
                System.out.println();
                return serverSocket;
            }
            else{
                return null;
            }
        }
        catch (Exception e) {
            Logger.getLogger(ServerConnectivity.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("Connection could not be opened..\n");
        }
        return null;
    }

    public static void closeConnection(Socket serverSocket) {
        try {
            serverSocket.close();
        } catch(Exception e) {
            Logger.getLogger(ServerConnectivity.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.println("serverSocket already closed\n");
        }
    }


}
