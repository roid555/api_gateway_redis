package com.example.api_gateway.controller;

import com.example.api_gateway.DTO.AppUserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
public class AppUserController {

    @PostMapping("/login")
    public void login(@RequestBody AppUserDTO user) {

    }

    @PostMapping("/register")
    public void register(@RequestBody AppUserDTO user) {

    }
}
