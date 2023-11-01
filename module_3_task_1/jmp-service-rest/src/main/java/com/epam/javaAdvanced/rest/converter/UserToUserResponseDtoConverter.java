package com.epam.javaAdvanced.rest.converter;

import com.epam.javaAdvanced.rest.domain.User;
import com.epam.javaAdvanced.rest.domain.UserResponseDto;
import org.springframework.core.convert.converter.Converter;

public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {
    @Override
    public UserResponseDto convert(User user) {
        return new UserResponseDto()
                .setId(user.getId())
                .setName(user.getName())
                .setSurname(user.getSurname())
                .setBirthday(user.getBirthday().toString());
    }
}
