package game.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class GetConfigProperties {
    public static String getValue(String fileName, String key) {
        String result = "";
        String propFileName = fileName + ".properties";
        InputStream inputStream = null;
        Properties p = new Properties();
        ClassLoader classLoader = GetConfigProperties.class.getClassLoader();
        try {
            System.out.println("zaczynam traja");
            p.load(new InputStreamReader(classLoader.getResourceAsStream(propFileName), StandardCharsets.UTF_8));
            System.out.println("w traju");
            result = p.getProperty(key);
            System.out.println("zara wyjde z traja");

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return result;
    }
}