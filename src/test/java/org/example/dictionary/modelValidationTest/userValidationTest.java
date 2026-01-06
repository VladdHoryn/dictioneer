package org.example.dictionary.modelValidationTest;

import jakarta.validation.*;
import org.example.dictionary.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.validation.Validator;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userValidationTest {
    private static jakarta.validation.Validator validator;
    private User validUser;

    @BeforeEach
    void init(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        validUser = new User();
        validUser.setName("Vlad");
        validUser.setEmail("uzerok88@gmail.com");
        validUser.setPassword("Password123");
        validUser.setRole("User");
    }

    @Test
    void createValidUser(){
        Set<ConstraintViolation<User>> violations = validator.validate(validUser);
        assertEquals(0, violations.size());
    }

    @ParameterizedTest
    @MethodSource("provideInvalidEmail")
    void createInvalidEmail(String email, String expectedMessage){
        User invalidUser = validUser;

        invalidUser.setEmail(email);

        Set<ConstraintViolation<User>> violations = validator.validate(invalidUser);

        for (var violation : violations){
            System.out.println("Property: " + violation.getPropertyPath() + ", Message: " + violation.getMessage());
        }

        assertEquals(1, violations.size());
        assertEquals(expectedMessage, violations.iterator().next().getMessage());
    }
    private static Stream<Arguments> provideInvalidEmail(){
        return Stream.of(
                Arguments.of("some email@gmail.com", "Email should be valid"),
                Arguments.of("someemailgmail.com", "Email should be valid"),
                Arguments.of("some email@gmailcom", "Email should be valid")
                );
    }
}
