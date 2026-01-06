package org.example.dictionary.modelValidationTest;

import jakarta.validation.*;
import org.example.dictionary.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.xml.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class userValidationTest {
    private static jakarta.validation.Validator validator;
    private static User validUser;

    @BeforeAll
    static void init(){
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
}
