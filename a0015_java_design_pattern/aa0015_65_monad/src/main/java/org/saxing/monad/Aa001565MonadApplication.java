package org.saxing.monad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import java.util.Objects;

public class Aa001565MonadApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(Aa001565MonadApplication.class);

    public static void main(String[] args) {
        User user = new User("user", 24, Sex.FEMALE, "foobar.com");
        LOGGER.info(Validator.of(user).validate(User::getName, Objects::nonNull, "name is null")
                .validate(User::getName, name -> !name.isEmpty(), "name is empty")
                .validate(User::getEmail, email -> !email.contains("@"), "email doesn't containt '@'")
                .validate(User::getAge, age -> age > 20 && age < 30, "age isn't between...").get().toString());
    }

}
