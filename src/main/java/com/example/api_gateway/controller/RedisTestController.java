package com.example.api_gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/redis-test")
@RequiredArgsConstructor
public class RedisTestController {
    private final RedisTemplate<String, String> redisTemplate;

    @GetMapping("/set")
    public String SetKey() {
        redisTemplate.opsForValue().set("test:key", "Hello Redis!", Duration.ofMinutes(10));
        return "Key set!";
    }

    @GetMapping("/get")
    public String getKey(){
        return redisTemplate.opsForValue().get("test:key");
    }
}
