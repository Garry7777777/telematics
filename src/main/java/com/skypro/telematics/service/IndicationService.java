package com.skypro.telematics.service;

import com.skypro.telematics.dto.LogIndication;
import com.skypro.telematics.dto.SerialSecretDTO;
import com.skypro.telematics.model.*;
import com.skypro.telematics.repository.IndicationRepository;
import com.skypro.telematics.repository.SerialSecretRepository;
import com.skypro.telematics.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.UUID;
import static java.time.temporal.ChronoUnit.SECONDS;

@Service
public class IndicationService {

    @Value("${telematika.security.token-expired}")
    private  Integer expiredSec;

    @Autowired
    private IndicationRepository indicationRepository;
    @Autowired
    private SerialSecretRepository serialSecretRepository;
    @Autowired
    private TokenRepository tokenRepository;

    public Double calculateAvgIndication(String serial) {
        return indicationRepository.getAvgIndication(serial);
    }

    public void save(SerialSecret serialSecret, LogIndication logIndication) {
        Indication indication = new Indication();
        indication.setValue(logIndication.getValue());
        indication.setSerialSecret(serialSecret);

        indicationRepository.save(indication);
    }

    public String login(SerialSecretDTO serialSecretDTO) {
        var serialSecret = serialSecretRepository.findById(serialSecretDTO.getSerial()).orElseThrow();
        if (!serialSecret.getSecret().equals(serialSecretDTO.getSecret())) throw new RuntimeException();
        Token token = new Token();
        var newUUID = UUID.randomUUID().toString();
        token.setUuid(newUUID);
        token.setExpiredDate(Instant.now().plus(expiredSec, SECONDS));
        token.setSerialSecret(serialSecret);
        tokenRepository.save(token);
        return newUUID;
    }
}