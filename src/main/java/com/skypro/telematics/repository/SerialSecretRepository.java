package com.skypro.telematics.repository;

import com.skypro.telematics.model.SerialSecret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SerialSecretRepository extends JpaRepository<SerialSecret, String> {
    Boolean existsBySecretAndSerial(String secret, String serial);
}