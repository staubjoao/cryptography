package com.api.cryptography.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userDocument;

    private String creditCardToken;

    @Column(name = "transaction_value")
    private Long value;
}
