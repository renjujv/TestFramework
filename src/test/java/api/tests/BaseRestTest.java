package api.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import util.ConfigHandler;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseRestTest {
    public static ConfigHandler configHandler = new ConfigHandler();
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {
        PrintStream logFile = null;
        System.out.println("Basic setup done for rest test steps...");
        try {
            logFile = new PrintStream(new FileOutputStream(configHandler.getProperty("log_file_path")));
        } catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
            if (requestSpec == null) {
                requestSpec = new RequestSpecBuilder()
                        .addFilter(RequestLoggingFilter.logRequestTo(logFile))
                        .addFilter(ResponseLoggingFilter.logResponseTo(logFile))
                        .log(LogDetail.ALL)
                        .build();
            }
            if (responseSpec == null) {
                responseSpec = new ResponseSpecBuilder()
                        .log(LogDetail.BODY)
                        .build();
            }

    }
}
