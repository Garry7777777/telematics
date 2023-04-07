package com.skypro.telematics.service;

import com.skypro.telematics.model.Token;
import com.skypro.telematics.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthenticationService {


    @Autowired
    private TokenRepository repository;


    public Optional<Token> findbyUUID(String uuid) {
        Optional<Token> token = repository.findByUuid(uuid);
        if(token.isPresent() && Instant.now().isBefore(token.get().getExpiredDate())){
            return token;
        }
        return Optional.empty();
    }
}