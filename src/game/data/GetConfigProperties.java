package game.data;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

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
}