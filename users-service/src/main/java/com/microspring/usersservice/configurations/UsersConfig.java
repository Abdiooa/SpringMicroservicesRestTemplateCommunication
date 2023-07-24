package com.microspring.usersservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UsersConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}