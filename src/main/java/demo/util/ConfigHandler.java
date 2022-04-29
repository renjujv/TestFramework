package demo.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

public class ConfigHandler {
    private final Properties properties = new Properties();
    private final String configFilePath = "src/test/resources/demo/config.properties";

    public String getProperty(String propertyKey){
        try (FileInputStream fileInStream = new FileInputStream(configFilePath)){
            properties.load(fileInStream);
            return properties.getProperty(propertyKey);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return Optional.empty().toString();
    }

    public void setProperty(String propertyKey, String propertyValue) {
        try (FileOutputStream fileOutStream = new FileOutputStream(configFilePath)){
            properties.setProperty(propertyKey, propertyValue);
            properties.store(fileOutStream,"Updated at:");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    public String getRuntimeProperty(String propertyKey) {
        Properties properties = new Properties();
        String lastCreatedUserID = "src/test/resources/demo/last_created_user_id";
        try (FileInputStream fileInStream = new FileInputStream(lastCreatedUserID)){
            properties.load(fileInStream);
            return properties.getProperty(propertyKey);
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        return Optional.empty().toString();
    }

    public void setRuntimeProperty(String propertyKey, String propertyValue) {
        Properties properties = new Properties();
        String lastCreatedUserID = "src/test/resources/demo/last_created_user_id";
        try (FileOutputStream fileOutStream = new FileOutputStream(lastCreatedUserID)){
            properties.setProperty(propertyKey, propertyValue);
            properties.store(fileOutStream,"Updated at:");
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
    }
}
