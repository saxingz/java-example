package org.saxing.converter;

import java.util.function.Function;

/**
 * user converter
 * 
 * @author saxing  2018/11/26 23:42
 */
public class UserConverter extends Converter<UserDto, User> {
    public UserConverter() {
        super(userDto -> new User(userDto.getFirstName(), userDto.getLastName(), userDto.isActive(), userDto.getEmail()),
                user -> new UserDto(user.getFirstName(), user.getLastName(), user.isActive(), user.getUserId()));
    }
}
