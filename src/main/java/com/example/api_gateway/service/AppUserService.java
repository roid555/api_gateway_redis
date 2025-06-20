package com.example.api_gateway.service;

import com.example.api_gateway.model.AppUser;
import com.example.api_gateway.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    @Autowired
    private AppUserRepository repository;


    @Cacheable(value = "users", key = "#email")
    public Optional<AppUser> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public AppUser register(AppUser user) {
        if(repository.findByEmail(user.getEmail()).isPresent())
            throw new IllegalStateException("Email already been use.");
        return repository.save(user);
    }
}
