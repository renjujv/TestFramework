package api.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import util.ConfigHandler;

public class BaseRestTest {
    public static ConfigHandler configHandler = new ConfigHandler();
    public static RequestSpecification requestSpec;
    public static ResponseSpecification responseSpec;

    @BeforeClass
    public static void setup() {
        if (requestSpec == null) {
            requestSpec = new RequestSpecBuilder().log(LogDetail.ALL).build();
        }
        if (responseSpec == null) {
            responseSpec = new ResponseSpecBuilder()
                    .log(LogDetail.BODY)
                    .build();
        }
        System.out.println("Basic setup done for rest test steps...");
    }
}
