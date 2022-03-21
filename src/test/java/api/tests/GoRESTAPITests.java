package api.tests;

import entities.User;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static util.EntityHandlers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoRESTAPITests extends BaseRestTest {

    @Test
    @Description("Get all available Users - check both the Status Code OK and Response Body contains User's names")
    @Severity(SeverityLevel.NORMAL)
    public void getAllUsers() {
        given().spec(requestSpec).auth().none()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .get(configHandler.getProperty("rest_base_uri") + "/users")
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_OK)
            .body(containsString("name"));
    }


    @Test
    @Description("Create a new User - check both the Status Code: CREATED and Response Body contains new User's name")
    @Severity(SeverityLevel.BLOCKER)
    public void createUser() {
        Response response = given().spec(requestSpec).auth().oauth2(configHandler.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .post(configHandler.getProperty("rest_base_uri") + "/users")
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_CREATED)
            .body(containsString("name"))
        .extract().response();

        configHandler.setProperty("last_created_user_id",response.body().jsonPath().get("id").toString());
    }

    @Test
    @Description("Update an existing User - check both the Status Code: OK and Response Body contains new User's name")
    @Severity(SeverityLevel.CRITICAL)
    public void updateUser() {
        given().spec(requestSpec).auth().oauth2(configHandler.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .patch(configHandler.getProperty("rest_base_uri") + "/users/2432")
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_OK)
            .body(containsString("name"));
    }


    @Test
    @Description("Delete an existing User - check the Status Code: NO_CONTENT")
    @Severity(SeverityLevel.NORMAL)
    public void deleteUser() {
        given().spec(requestSpec).auth().oauth2(configHandler.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .delete(configHandler.getProperty("rest_base_uri") + "/users/"+ configHandler.getProperty("last_created_user_id"))
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
