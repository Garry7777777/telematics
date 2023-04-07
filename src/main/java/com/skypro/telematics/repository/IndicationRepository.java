package com.skypro.telematics.repository;

import com.skypro.telematics.model.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationRepository extends JpaRepository<Indication, Long> {
    @Query(value = "SELECT AVG(i.value) FROM Indication i WHERE i.serialSecret.serial=?1")
    Double getAvgIndication(String serial);
}