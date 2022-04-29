package demo.api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static demo.util.EntityHandlers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GoRESTAPITests extends BaseRestTest {


    //TODO Need to implement test ordering in JUnit4 or use JUnit5
    @Test
    @Description("Get all available Users - check both the Status Code OK and Response Body contains User's names")
    @Severity(SeverityLevel.NORMAL)
    public void AgetAllUsers() {
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
    public void BcreateUser() {
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

        String lastCreatedUserId = response.body().jsonPath().get("id").toString();
        configHandler.setRuntimeProperty("last_created_user_id", lastCreatedUserId);
    }


    @Test
    @Description("Update an existing User - check both the Status Code: OK and Response Body contains new User's name")
    @Severity(SeverityLevel.CRITICAL)
    public void CupdateUser() {
        given().spec(requestSpec).auth().oauth2(configHandler.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .patch(configHandler.getProperty("rest_base_uri") + "/users/"+ configHandler.getRuntimeProperty("last_created_user_id"))
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_OK)
            .body(containsString("name"));
    }


    @Test
    @Description("Delete an existing User - check the Status Code: NO_CONTENT")
    @Severity(SeverityLevel.NORMAL)
    public void DdeleteUser() {
        given().spec(requestSpec).auth().oauth2(configHandler.getProperty("auth_token"))
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
        .when()
            .delete(configHandler.getProperty("rest_base_uri") + "/users/"+ configHandler.getRuntimeProperty("last_created_user_id"))
        .then().spec(responseSpec)
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
