package game.Constant;

import game.data.GetConfigProperties;

import static java.lang.Integer.parseInt;

/**
 * Klasa przechoowywująca domyślne stałe gry
 */
public class DefaultGameSettings {
    /** string prechowywujący nazwę pliku z ogolnymi stałymi gry. BEZ ROZSZERZENIA */
    private static final String fileName = "defaultGameSettings";

    /** stała przechowywująca domyślna szerokość okna gry */
    public static final int WIDTH = parseInt(GetConfigProperties.getValue(fileName, "width"));
    /** stała przechowywująca domyślna wysokość okna gry */
    public static final int HEIGHT = parseInt(GetConfigProperties.getValue(fileName, "height"));
    /** stała przechowywująca maksymalną ilość żyć gracza */
    public static final int LIFES = parseInt(GetConfigProperties.getValue(fileName, "lifes"));
    /** stała przechowywująca liczbę leveli gry */
    public static final int NUMBEROFLEVELS = parseInt(GetConfigProperties.getValue(fileName, "numberOfLevels"));
    /** stała przechowywująca maksymalny poziom paliwa statku */
    public static final float FUEL = Float.parseFloat(GetConfigProperties.getValue(fileName, "fuelLevel"));
    /** stała przechowywująca liczbę S punktów (za niezniszczenie staku) */
    public static final int S_POINTS = Integer.parseInt(GetConfigProperties.getValue(fileName, "S"));
    /** stała przechowywujaca maksymalną prędkośc lądowania*/
    public static final int MAX_SPEED_LANDING = Integer.parseInt(GetConfigProperties.getValue(fileName, "maxLandingSpeed"));
    /** stala przechowywująca początkową prędkośc statku */
    public static final float SPEED_ACCELERATING = Float.parseFloat(GetConfigProperties.getValue(fileName, "speedAccelerating"));
    /** stała przechowywująca startową prędkość X */
    public static final int START_SPEED_X = Integer.parseInt(GetConfigProperties.getValue(fileName, "startSpeedX"));
    /** stała przechowywująca startową prędkość Y */
    public static final int START_SPEED_Y = Integer.parseInt(GetConfigProperties.getValue(fileName, "startSpeedY"));


    private DefaultGameSettings() {
        throw new AssertionError();
    }
}
