package com.skypro.telematics.scheduler;

import com.skypro.telematics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;

@Component
public class ClearToken {

    @Autowired
    private TokenRepository tokenRepository;

//    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void clearTokens() {
        tokenRepository.deleteAllByExpiredDateIsBefore(Instant.now());
    }
}
