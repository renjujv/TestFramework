package util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import entities.Gender;
import entities.User;
import entities.UserStatus;

import java.util.Objects;

public class EntityHandlers {

    public static User getRandomUser(){
        Faker faker = new Faker();
        return new User(
                faker.name().fullName(),
                Gender.Male.toString(),
                faker.internet().emailAddress(),
                UserStatus.Active.toString()
        );
    }

    /**
     * Create a random User and get the user data as JSON
     * @return JSON String
     */
    public static String getUserAsJSON(User user) {
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.writeValueAsString(Objects.requireNonNullElseGet(user, EntityHandlers::getRandomUser));
        } catch(JsonProcessingException jsonProcessingException){
            System.out.println(jsonProcessingException.getMessage());
            return "{}";
        }
    }
}

