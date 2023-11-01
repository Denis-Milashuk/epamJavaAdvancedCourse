package com.epam.javaAdvanced.rest.converter;

import com.epam.javaAdvanced.rest.api.UserService;
import com.epam.javaAdvanced.rest.domain.Subscription;
import com.epam.javaAdvanced.rest.domain.SubscriptionRequestDto;
import com.epam.javaAdvanced.rest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    private final UserService userService;

    @Override
    public Subscription convert(SubscriptionRequestDto subscriptionRequestDto) {

        Optional<User> user = userService.getUser(subscriptionRequestDto.userId());

        return new Subscription()
                .setId(subscriptionRequestDto.id())
                .setStartDate(LocalDate.parse(subscriptionRequestDto.startDate()))
                .setUser(user.orElse(null));
    }
}
