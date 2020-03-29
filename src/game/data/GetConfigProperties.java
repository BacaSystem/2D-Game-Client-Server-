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
        Properties p = new Properties();
        try {
            p.load(new InputStreamReader(GetConfigProperties.class.getClassLoader().getResourceAsStream(propFileName), StandardCharsets.UTF_8));
            result = p.getProperty(key);

        } catch (Exception e) {
            System.out.println("Error: " + e + result);
        }
        return result;
    }
}