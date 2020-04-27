package server.configReader;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {
    /**
     * Metoda statyczna pobierająca dane z pliku konfiguracyjnego i zwracająca String spod klucza
     * Metoda ma zabezpieczenia przed próbą otworzenia nieistniejącego pliku i przed wyszukaniem nieistniejącego klucza
     * @param fileName nazwa pliku konfiguracyjnego, z ktorego zostanie pobrana dana. BEZ ROZSZERZENIA
     * @param key klucz, jakiego metoda będzie szukać w pliku konfiguracyjnym
     * @return zwraca String wartości spod klucza
     */
    public static String getValue(String fileName, String key) {
        String result = "";
        String propFileName = "server/config/" + fileName + ".properties";
        Properties p = new Properties();
        BufferedReader in = null;
        File file;
        try {
            file = new File(propFileName);

            in = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(file), "UTF8"));
            p.load(in);
            result = p.getProperty(key);
            if (result == null) {
                System.out.println("WARNING, empty key '" + key + "' or doesn't exist in config '" + fileName + "'");
            }
        } catch (Exception e) {
            String errorText = "Error, File '" + fileName + "' not found";
            System.out.println(errorText);
            result = "FATAL_ERROR";
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    // NA RAZIE NIE POTRZEBNA, ALE MOŻE SIE PRZYDA

    /**
     * Metoda zapisująca dane do pliku konfiguracyjnego
     * @param fileName nazwa pliku BEZ ROZSZERZEŃ, w ktorym zostanie zapisana informacja
     * @param key klucz, pod jakim zostanie zapisana informacja
     * @param data dana, ktora zostanie zapisana w pliku
     */
    public static boolean setValue(String fileName, String key, String data) {
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        String propFileName = "server/config/" +fileName + ".properties";
        try {
            Properties configProperty = new Properties();

            File file = new File(propFileName);
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(key, data);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "");

        } catch (Exception ex) {
            Logger.getLogger(game.configReader.ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {

            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(game.configReader.ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
}
