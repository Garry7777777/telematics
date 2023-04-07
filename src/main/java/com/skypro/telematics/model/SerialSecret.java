package com.skypro.telematics.model;

import lombok.*;
import javax.persistence.*;

@Entity
@Data
public class SerialSecret {
    @Id
    private String serial;
    private String secret;

}
