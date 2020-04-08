package game.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Klasa zanjmująca się odczytem i zapisem danych z plików konfiguracyjnych
 */
public class GetConfigProperties {

    /**
     * Metoda statyczna pobierająca dane z pliku konfiguracyjnego i zwracająca String spod klucza
     * Metoda ma zabezpieczenia przed próbą otworzenia nieistniejącego pliku i przed wyszukaniem nieistniejącego klucza
     * @param fileName nazwa pliku konfiguracyjnego, z ktorego zostanie pobrana dana. BEZ ROZSZERZENIA
     * @param key klucz, jakiego metoda będzie szukać w pliku konfiguracyjnym
     * @return zwraca String wartości spod klucza
     */
    public static String getValue(String fileName, String key) {
        String result = "";
        String propFileName = fileName + ".properties";
        Properties p = new Properties();
        InputStreamReader streamReader = null;
        try {
            streamReader = new InputStreamReader(GetConfigProperties.class.getClassLoader().getResourceAsStream(propFileName), StandardCharsets.UTF_8);
            p.load(streamReader);
            result = p.getProperty(key);
            if (result == null) {
                System.out.println("WARNING, empty key '" + key + "' or doesn't exist in config '" + fileName + "'");
            }
        } catch (Exception e) {
            System.out.println("Error, File '" + fileName + "' not found" + e);   // AB: jesli pliku z konfigurają nie udało się wczytać (np. bo resources nie dodane do classpath) to chyba trzeba zakonczy gre?
        }                                                                         // gra i tak za chwile się wyłłoży, a ten komunikat zginie w innych komuinikatach
        return result;
    }

    // NA RAZIE NIE POTRZEBNA, ALE MOŻE SIE PRZYDA

    /**
     * Metoda zapisująca dane do pliku konfiguracyjnego
     * @param fileName nazwa pliku BEZ ROZSZERZEŃ, w ktorym zostanie zapisana informacja
     * @param key klucz, pod jakim zostanie zapisana informacja
     * @param data dana, ktora zostanie zapisana w pliku
     */
    public static void setValue(String fileName, String key, String data) {
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        String propFileName ="resources/" +fileName +".properties";
        try {
            Properties configProperty = new Properties();

            File file = new File(propFileName);
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty(key, data);
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "");

        } catch (Exception ex) {
            Logger.getLogger(GetConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            try {
                fileOut.close();
            } catch (IOException ex) {
                Logger.getLogger(GetConfigProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
 }