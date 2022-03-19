package rest.tests;

import entities.User;
import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static util.EntityHandlers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoRESTTests extends BaseRestTest {
    private final String authToken = "0c74a543d29e7d93cd29ba015f67f6cffb85505cbd21e088e5343597257061fb";
    private final String baseURL = "https://gorest.co.in/public/v2";


    @Test
    public void getAllUsers() {
        given().spec(requestSpec).auth().none()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .get(config.getProperty("rest_base_uri") + "/users")
        .then().spec(responseSpec)
            .statusCode(200)
            .body(containsString("name"));
    }


    @Test
    public void createUser() {
        Response response = given().spec(requestSpec).auth().oauth2(config.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .post(config.getProperty("rest_base_uri") + "/users")
        .then().spec(responseSpec)
            .statusCode(201)
            .body(containsString("name"))
        .extract().response();

        config.setProperty("last_created_user_id",response.body().jsonPath().get("id").toString());
    }

    @Test
    public void updateUser() {
        User updatedUser = new User("Allasani Peddana","Female","allasani.peddana@15ce.com","active");

        given().spec(requestSpec).auth().oauth2(config.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(updatedUser))
            .patch(config.getProperty("rest_base_uri") + "/users/2432")
        .then().spec(responseSpec)
            .statusCode(200)
            .body(containsString("name"));
    }


    @Test
    public void deleteUser() {
        given().spec(requestSpec).auth().oauth2(config.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .delete(config.getProperty("rest_base_uri") + "/users/"+config.getProperty("last_created_user_id"))
        .then().spec(responseSpec)
            .statusCode(204);
    }
}
