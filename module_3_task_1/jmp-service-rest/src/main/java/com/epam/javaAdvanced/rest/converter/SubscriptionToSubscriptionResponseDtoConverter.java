package com.epam.javaAdvanced.rest.converter;

import com.epam.javaAdvanced.rest.domain.Subscription;
import com.epam.javaAdvanced.rest.domain.SubscriptionResponseDto;
import com.epam.javaAdvanced.rest.domain.User;
import org.springframework.core.convert.converter.Converter;

import java.util.Optional;

public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {
    @Override
    public SubscriptionResponseDto convert(Subscription subscription) {
        return new SubscriptionResponseDto()
                .setId(subscription.getId())
                .setUserId(Optional.ofNullable(subscription.getUser())
                        .map(User::getId)
                        .orElse(null))
                .setStartDate(subscription.getStartDate().toString());
    }
}
