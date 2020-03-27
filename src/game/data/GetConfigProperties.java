package game.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class GetConfigProperties {
    public static String getValue(String fileName, String key) throws IOException {
        String result = "";
        InputStream inputStream = null;
        Properties p = new Properties();
        ClassLoader classLoader = GetConfigProperties.class.getClassLoader();
        try {
            String propFileName = fileName + ".properties";

            inputStream = classLoader.getResourceAsStream(propFileName);
            if (inputStream != null) {
                p.load(new InputStreamReader(classLoader.getResourceAsStream(propFileName), StandardCharsets.UTF_8));
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            result = p.getProperty(key);

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            assert inputStream != null;
            inputStream.close();
        }
        return result;
    }
}