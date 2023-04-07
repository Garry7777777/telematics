package com.skypro.telematics.model;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
public class Token {

    @Id
    private String uuid;
    private Instant expiredDate;
    @ManyToOne
    private SerialSecret serialSecret;
}
