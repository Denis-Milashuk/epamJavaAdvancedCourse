package com.epam.javaAdvanced.rest.config;

import com.epam.javaAdvanced.rest.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.epam.javaAdvanced.rest.converter.SubscriptionToSubscriptionResponseDtoConverter;
import com.epam.javaAdvanced.rest.converter.UserRequestDtoToUserConverter;
import com.epam.javaAdvanced.rest.converter.UserToUserResponseDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SubscriptionRequestDtoToSubscriptionConverter subscriptionRequestDtoToSubscriptionConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(this.subscriptionRequestDtoToSubscriptionConverter);
        registry.addConverter(new SubscriptionToSubscriptionResponseDtoConverter());
        registry.addConverter(new UserRequestDtoToUserConverter());
        registry.addConverter(new UserToUserResponseDtoConverter());
    }
}
