package com.microspring.departementservice.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DepartementConfig {
    @Bean
    public ModelMapper modelMapperBean(){
        return new ModelMapper();
    }
}
