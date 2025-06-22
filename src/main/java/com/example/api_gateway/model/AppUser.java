package com.example.api_gateway.model;


import com.example.api_gateway.DTO.AppUserDTO;
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

    public AppUserDTO toDTO() {
        return new AppUserDTO(email, password, name);
    }
}
