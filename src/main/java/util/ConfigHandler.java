package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigHandler {
    private final Properties properties = new Properties();
    private final String configFilePath = "src/test/resources/config.properties";

    public String getProperty(String propertyKey){
        try (FileInputStream fileInStream = new FileInputStream(configFilePath)){
            properties.load(fileInStream);
            return properties.getProperty(propertyKey);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return "";
    }

    public void setProperty(String propertyKey, String propertyValue) {
        try (FileOutputStream fileOutStream = new FileOutputStream(configFilePath)){
            properties.setProperty(propertyKey,propertyValue).toString();
            properties.store(fileOutStream,"Updated at:");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
