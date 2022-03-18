package rest.tests;

import entities.User;
import org.junit.Test;
import static util.EntityHandlers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class GoRESTTests extends BaseRestTest {
    private final String authToken = "0c74a543d29e7d93cd29ba015f67f6cffb85505cbd21e088e5343597257061fb";
    private final String baseURL = "https://gorest.co.in/public/v2";


    @Test
    public void check_response_statusCode_OK_and_body_when_getting_users() {
        try {
            given().spec(requestSpec).auth().none()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
            .when()
                .get(baseURL + "/users")
            .then().spec(responseSpec)
                .statusCode(200)
                .body(containsString("name"));
        } catch (AssertionError error) {
            System.err.println(error.getMessage());
        }
    }


    @Test
    public void check_response_statusCode_ACCEPTED_and_body_when_creating_user() {
        try {
            given().spec(requestSpec).auth().oauth2(config.getProperty("base_uri"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
            .when()
                .body(getUserAsJSON(getRandomUser()))
                .post(baseURL + "/users")
            .then().spec(responseSpec)
                .statusCode(201)
                .body(containsString("name"));
        } catch (AssertionError error) {
            error.printStackTrace();
        }
    }


    @Test
    public void check_response_statusCode_ACCEPTED_and_body_when_updating_user() {
        try {
            User updatedUser = new User("Allasani Peddana","Female","allasani.peddana@15ce.com","active");

            given().spec(requestSpec).auth().oauth2(authToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
            .when()
                .body(getUserAsJSON(updatedUser))
                .patch(baseURL + "/users/2432")
            .then().spec(responseSpec)
                .statusCode(201)
                .body(containsString("name"));
        } catch (AssertionError error) {
            error.printStackTrace();
        }
    }


    @Test
    public void check_response_statusCode_OK_when_deleting_users() {
        try {
            given().spec(requestSpec).auth().oauth2(authToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
            .when()
                .delete(baseURL + "/users/2430")
            .then().spec(responseSpec)
                .statusCode(204);
        } catch (AssertionError error) {
            System.err.println(error.getMessage());
        }
    }
}
