package com.algoExpert.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class PersistentLogins {

    private String username;
    @Id
    private String series;
    private String token;
    private Timestamp last_used;
}
