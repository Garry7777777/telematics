package com.skypro.telematics.model;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Indication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long value;
    private Instant createDate = Instant.now();
    @ManyToOne
    private SerialSecret serialSecret;

}