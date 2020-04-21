package game.Constant;

import configReader.GetConfigProperties;
import configReader.ServerReader;

import java.net.Socket;
import java.util.Map;

import static java.lang.Integer.parseInt;

/**
 * Klasa przechoowywująca domyślne stałe gry
 */
public class DefaultGameSettings {
    private static final String serverCommand = "GAME_SETTINGS";
    /** string prechowywujący nazwę pliku z ogolnymi stałymi gry. BEZ ROZSZERZENIA */
    private static final String fileName = "defaultGameSettings";

    /** stała przechowywująca domyślna szerokość okna gry */
    public static int WIDTH;
    /** stała przechowywująca domyślna wysokość okna gry */
    public static int HEIGHT;
    /** stała przechowywująca maksymalną ilość żyć gracza */
    public static int LIFES;
    /** stała przechowywująca liczbę leveli gry */
    public static int NUMBEROFLEVELS;
    /** stała przechowywująca maksymalny poziom paliwa statku */
    public static float FUEL;
    /** stała przechowywująca liczbę S punktów (za niezniszczenie staku) */
    public static int S_POINTS;
    /** stała przechowywujaca maksymalną prędkośc lądowania*/
    public static int MAX_SPEED_LANDING;
    /** stala przechowywująca początkową prędkośc statku */
    public static float SPEED_ACCELERATING;
    /** stała przechowywująca startową prędkość X */
    public static int START_SPEED_X;
    /** stała przechowywująca startową prędkość Y */
    public static int START_SPEED_Y;


    private DefaultGameSettings() {
        throw new AssertionError();
    }

    public static void donwloanGameSettings(Socket serverSocket) {
        if(serverSocket!= null) {
            System.out.println("settings From server");

            Map<String,String> gameSettings = ServerReader.getDecodedDataInMap(serverSocket,serverCommand);

            WIDTH = parseInt(gameSettings.get("width"));
            HEIGHT = parseInt(gameSettings.get("height"));
            LIFES = parseInt(gameSettings.get("lifes"));
            NUMBEROFLEVELS = parseInt(gameSettings.get("numberOfLevels"));
            FUEL = Float.parseFloat(gameSettings.get("fuelLevel"));
            S_POINTS = parseInt(gameSettings.get("S"));
            MAX_SPEED_LANDING = Integer.parseInt(gameSettings.get("maxLandingSpeed"));
            SPEED_ACCELERATING = Float.parseFloat(gameSettings.get("speedAccelerating"));
            START_SPEED_X = Integer.parseInt(gameSettings.get("startSpeedX"));
            START_SPEED_Y = Integer.parseInt(gameSettings.get("startSpeedY"));

        } else {
            System.out.println("Offline Settings");

            WIDTH = parseInt(GetConfigProperties.getValue(fileName, "width"));
            HEIGHT = parseInt(GetConfigProperties.getValue(fileName, "height"));
            LIFES = parseInt(GetConfigProperties.getValue(fileName, "lifes"));
            NUMBEROFLEVELS = parseInt(GetConfigProperties.getValue(fileName, "numberOfLevels"));
            FUEL = Float.parseFloat(GetConfigProperties.getValue(fileName, "fuelLevel"));
            S_POINTS = Integer.parseInt(GetConfigProperties.getValue(fileName, "S"));
            MAX_SPEED_LANDING = Integer.parseInt(GetConfigProperties.getValue(fileName, "maxLandingSpeed"));
            SPEED_ACCELERATING = Float.parseFloat(GetConfigProperties.getValue(fileName, "speedAccelerating"));
            START_SPEED_X = Integer.parseInt(GetConfigProperties.getValue(fileName, "startSpeedX"));
            START_SPEED_Y = Integer.parseInt(GetConfigProperties.getValue(fileName, "startSpeedY"));

        }
    }
}
