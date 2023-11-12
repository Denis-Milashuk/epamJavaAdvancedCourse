package com.epam.javaAdvanced.security.service;

import com.epam.javaAdvanced.security.domain.AttemptsData;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginAttemptService {

    private static final int MAX_ATTEMPTS = 3;
    private static final int BLOCK_DURATION_SEC = 60 * 5;
    private final Map<String, AttemptsData> attemptsDataMap = new HashMap<>();

    public void loginFailed(String userName) {
        attemptsDataMap.compute(userName, (key, attemptsData) -> {
            if (attemptsData == null) attemptsData = new AttemptsData().setNumberOfAttempts(1);

            if (attemptsData.getNumberOfAttempts() >= MAX_ATTEMPTS) {
                attemptsData.setBlockedUntil(Instant.now().plus(BLOCK_DURATION_SEC, ChronoUnit.SECONDS));
            } else {
                attemptsData.increment();
            }

            return attemptsData;
        });
    }

    public boolean isBlocked(String userName) {
        AttemptsData attemptsData = attemptsDataMap.get(userName);
        if (attemptsData == null || attemptsData.getBlockedUntil() == null) {
            return false;
        }

        if (attemptsData.getBlockedUntil().isBefore(Instant.now())) {
            attemptsData.reset();
            return false;
        }

        return true;
    }

    public List<String> getBlocked() {
        return attemptsDataMap
                .keySet()
                .stream()
                .filter((this::isBlocked))
                .toList();
    }
}
