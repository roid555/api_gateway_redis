package com.example.api_gateway.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class AppUser {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String email;
    private String password;
}
