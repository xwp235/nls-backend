package com.nlsapi.core.business.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping
    public String index(String username) {
        return "Hello, youmi api!";
    }

    @PostMapping
    public String index2(@RequestBody User data) {
        return "Hello, youmi api!";
    }

    @Data
    public static class User {
        private String username;
        private String password;
    }

}
