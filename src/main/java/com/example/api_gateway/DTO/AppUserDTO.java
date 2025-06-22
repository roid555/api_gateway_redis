package com.example.api_gateway.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AppUserDTO {
    @NotBlank
    public String email;
    @NotBlank
    public String password;
    @NotBlank
    public String name;


}
