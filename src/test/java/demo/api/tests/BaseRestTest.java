package demo.api.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import demo.util.ConfigHandler;
import org.testng.annotations.BeforeClass;

import java.util.Optional;

public class BaseRestTest {
    public static ConfigHandler configHandler = ConfigHandler.getInstance();
    public static Optional<RequestSpecification> requestSpec = Optional.empty();
    public static Optional<ResponseSpecification> responseSpec = Optional.empty();

    @BeforeClass
    public void setup() {
        if (requestSpec.isEmpty()) {
            requestSpec = Optional.ofNullable(new RequestSpecBuilder()
                    .log(LogDetail.URI)
                    .log(LogDetail.METHOD)
                    .build());
        }
        if (responseSpec.isEmpty()) {
            responseSpec = Optional.ofNullable(new ResponseSpecBuilder()
                    .log(LogDetail.STATUS)
                    .log(LogDetail.BODY)
                    .build());
        }
        System.out.println("Basic setup done for rest test steps...");
    }

    public RequestSpecification getEmptyRequestSpec(){
        return new RequestSpecBuilder().build();
    }

    public ResponseSpecification getEmptyResponseSpec(){
        return new ResponseSpecBuilder().build();
    }
}
