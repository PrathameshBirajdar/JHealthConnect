package com.jhealthconnect.backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @GetMapping
    public Map<String, String> getHomeData() {
        Map<String, String> data = new HashMap<>();
        data.put("welcome", "Welcome to JHealthConnect!");
        data.put("message", "Your Health, Our Priority");
        return data;
    }
}