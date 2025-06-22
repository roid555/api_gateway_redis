package com.example.api_gateway.service;

import com.example.api_gateway.DTO.AppUserDTO;
import com.example.api_gateway.model.AppUser;
import com.example.api_gateway.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private AppUserRepository repository;
    private final RedisTemplate<String, AppUserDTO> redisTemplate;


    @Cacheable(value = "users", key = "#email")
    public AppUserDTO findByEmail(String email) throws Exception {

        // 1. Try Redis
        String key = getRedisKey(email);
        AppUserDTO cached = redisTemplate.opsForValue().get(key);
        if (cached != null) return cached;

        // 2. If not found, go to DB
        AppUser user = repository.findByEmail(email)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

        AppUserDTO dto = user.toDTO();

        // 3. Cache result
        redisTemplate.opsForValue().set(key, dto, Duration.ofMinutes(30));

        return dto;

    }

    public AppUser register(AppUser user) {
        // check if email exist
        if(isEmailExist(user.getEmail()))
            throw new IllegalStateException("Email already been use.");
        // TODO: can add more tests to user data - valid name, password etc.
        // create the user if not
        AppUser saved = repository.save(user);
        // and cache it
        redisTemplate.opsForValue().set(
                getRedisKey(saved.getEmail()), saved.toDTO(), Duration.ofMinutes(30)
        );
        return saved;
    }

    private boolean isEmailExist(String email) {
        String key = getRedisKey(email);
        AppUserDTO cached = redisTemplate.opsForValue().get(key);
        if (cached != null) return true;
        return repository.findByEmail(email).isPresent();

    }

    private String getRedisKey(String email) {
        return "user:" + email;
    }
}
