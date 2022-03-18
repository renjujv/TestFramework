package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    public Properties properties;

    public String getProperty(String propertyKey) {
        String propertyValue = "";
        try {
            FileInputStream file = new FileInputStream("src/main/resources/config.properties");
            properties = new Properties();
            properties.load(file);
            propertyValue = properties.getProperty(propertyKey);
        } catch (FileNotFoundException e) {
            System.out.println("Config file was not found");
            e.printStackTrace();

        } catch (IOException e) {
            System.out.println("Error occured while reading the Config file");
            e.printStackTrace();
        }
        return propertyValue;
    }
}
