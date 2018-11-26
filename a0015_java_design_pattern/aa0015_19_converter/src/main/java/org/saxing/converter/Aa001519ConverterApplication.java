package org.saxing.converter;

import com.google.common.collect.Lists;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * main
 *
 * @author saxing  2018/11/26 23:43
 */
public class Aa001519ConverterApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(Aa001519ConverterApplication.class, args);
//    }

    public static void main(String[] args) {
        Converter<UserDto, User> userConverter = new UserConverter();

        UserDto dtoUser = new UserDto("John", "Doe", true, "whatever[at]wherever.com");
        User user = userConverter.convertFromDto(dtoUser);
        System.out.println("Entity converted from DTO:" + user);

        ArrayList<User> users = Lists.newArrayList(new User("Camile", "Tough", false, "124sad"),
                new User("Marti", "Luther", true, "42309fd"), new User("Kate", "Smith", true, "if0243"));
        System.out.println("Domain entities:");
        users.forEach(System.out::println);


        System.out.println("DTO entities converted from domain:");
        List<UserDto> dtoEntities = userConverter.createFromEntites(users);
        dtoEntities.forEach(System.out::println);

    }

}
