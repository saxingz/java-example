package org.saxing.converter;

import java.util.function.Function;

public class UserConverter extends Converter<UserDto, User> {
    public UserConverter(Function<UserDto, User> fromDto, Function<User, UserDto> fromEntity) {
        super(userDto -> new User(userDto.getFirstName(), userDto.getLastName(), userDto.isActive(), userDto.getEmail()),
                user -> new UserDto(user.getFirstName(), user.getLastName(), user.getIsActive(), user.getUserId()));
    }
}
