package demo.api.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static demo.util.EntityHandlers.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.containsString;

public class GoRESTAPITests extends BaseRestTest {

    //TODO Need to implement test ordering in JUnit4 or use JUnit5
    @Test() @Severity(SeverityLevel.NORMAL)
    @Description("Get all available Users - check both the Status Code OK and Response Body contains User's names")
    public void test001_getAllUsers() {
        given()
                .spec(requestSpec.orElse(getEmptyRequestSpec())).auth().none()
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
        .when()
            .get(configHandler.getProperty("rest_base_uri") + "/users")
        .then()
                .spec(responseSpec.orElse(getEmptyResponseSpec()))
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("name"));
    }


    @Test(groups = {"data-gen"}) @Severity(SeverityLevel.BLOCKER)
    @Description("Create a new User - check both the Status Code: CREATED and Response Body contains new User's name")
    public void test002_createUser() {
        Response response = given()
                .spec(requestSpec.orElse(getEmptyRequestSpec())).auth().oauth2(configHandler.getProperty("auth_token"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .post(configHandler.getProperty("rest_base_uri") + "/users")
        .then()
                .spec(responseSpec.orElse(getEmptyResponseSpec()))
                .statusCode(HttpStatus.SC_CREATED)
                .body(containsString("name"))
                .extract().response();

        String lastCreatedUserId = response.body().jsonPath().get("id").toString();
        configHandler.setRuntimeProperty("last_created_user_id", lastCreatedUserId);
    }


    @Test() @Severity(SeverityLevel.CRITICAL)
    @Description("Update an existing User - check both the Status Code: OK and Response Body contains new User's name")
    public void test003_updateUser() {
        given()
                .spec(requestSpec.orElse(getEmptyRequestSpec()))
                .auth().oauth2(configHandler.getProperty("auth_token"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
        .when()
            .body(getUserAsJSON(getRandomUser()))
            .patch(configHandler.getProperty("rest_base_uri") + "/users/"+
                    configHandler.getRuntimeProperty("last_created_user_id"))
        .then()
                .spec(responseSpec.orElse(getEmptyResponseSpec()))
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("name"));
    }


    @Test() @Severity(SeverityLevel.NORMAL)
    @Description("Delete an existing User - check the Status Code: NO_CONTENT")
    public void test004_deleteUser() {
        given()
                .spec(requestSpec.orElse(getEmptyRequestSpec()))
                .auth().oauth2(configHandler.getProperty("auth_token"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
        .when()
            .delete(configHandler.getProperty("rest_base_uri") + "/users/" +
                    configHandler.getRuntimeProperty("last_created_user_id"))
        .then()
                .spec(responseSpec.orElse(getEmptyResponseSpec()))
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }
}
