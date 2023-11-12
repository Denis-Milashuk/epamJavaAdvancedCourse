package com.epam.javaAdvanced.security.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class AttemptsData {
    private int numberOfAttempts;
    private Instant blockedUntil;

    public void increment() {
        numberOfAttempts++;
    }

    public void reset() {
        numberOfAttempts = 0;
        blockedUntil = null;
    }
}
