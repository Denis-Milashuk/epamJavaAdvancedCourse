package com.epam.javaAdvanced.rest.api;

import com.epam.javaAdvanced.rest.domain.Subscription;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    Optional<Subscription> getSubscription(Long id);
    List<Subscription> getAllSubscriptions();
    Subscription save(Subscription subscription);
    void deleteSubscription(Long id);
}
