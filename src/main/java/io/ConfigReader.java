package io;

import java.io.*;
import java.util.Properties;

/**
 * @author Derek Argueta
 */
public class ConfigReader {

    public static Properties readConfig() {
        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("db.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return properties;
    }
}
