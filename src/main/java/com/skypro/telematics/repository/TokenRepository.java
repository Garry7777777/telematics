package com.skypro.telematics.repository;


import com.skypro.telematics.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    void deleteAllByExpiredDateIsBefore(Instant now);

    Optional<Token> findByUuid(String uuid);

}
