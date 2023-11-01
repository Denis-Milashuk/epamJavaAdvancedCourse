package com.epam.javaAdvanced.rest.converter;

import com.epam.javaAdvanced.rest.domain.User;
import com.epam.javaAdvanced.rest.domain.UserRequestDto;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    @Override
    public User convert(UserRequestDto userRequestDto) {
        return new User()
                .setId(userRequestDto.id())
                .setName(userRequestDto.name())
                .setSurname(userRequestDto.surname())
                .setBirthday(LocalDate.parse(userRequestDto.birthday()));
    }
}
