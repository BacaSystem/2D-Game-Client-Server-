package game.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

public class GetConfigProperties {

    public static String getValue(String fileName, String key) {
        String result = "";
        String propFileName = fileName + ".properties";
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(GetConfigProperties.class.getClassLoader().getResourceAsStream(propFileName), StandardCharsets.UTF_8));
            result = p.getProperty(key);
            if (result == null) {
                System.out.println("WARNING, empty key '" + key + "' or doesn't exist in config '" + fileName + "'");
            }
        } catch (Exception e) {
            System.out.println("Error, File '" + fileName + "' not found" + e );
        }
        return result;
    }
/*
    public static void setValue(String filename) {
        String propFileName = filename +".properties";
        Properties prop = new Properties();
        try {
            prop.setProperty("nick1", "Kryzysiek");
            prop.store(new FileOutputStream(propFileName), null);
        } catch(Exception e) {
            System.out.println("ERROR: "+ e);
        }

    }

 */
    public static void setValue(String fileName /*String key, String data*/) {
        FileOutputStream fileOut = null;
        FileInputStream fileIn = null;
        String propFileName ="resources/" +fileName +".properties";
        try {
            Properties configProperty = new Properties();

            File file = new File(propFileName);
            fileIn = new FileInputStream(file);
            configProperty.load(fileIn);
            configProperty.setProperty("nick1", "Eustachy");
            fileOut = new FileOutputStream(file);
            configProperty.store(fileOut, "sample properties");

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