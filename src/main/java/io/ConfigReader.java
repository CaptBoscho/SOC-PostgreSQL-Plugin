package io;

import java.io.*;
import java.util.Properties;

/**
 * @author Derek Argueta
 */
public class ConfigReader {

    public static Properties readConfig() {
        Properties properties = new Properties();
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(new File("db.properties"));
//            properties.load(inputStream);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if(inputStream != null) {
//                try {
//                    inputStream.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        properties.setProperty("dbname", "test");
        properties.setProperty("username", "soc");
        properties.setProperty("password", "settlersofswag");
        properties.setProperty("hostname", "soc-test.cinp8shdrsvf.us-east-1.rds.amazonaws.com");
        properties.setProperty("port", "5432");

        return properties;
    }
}
