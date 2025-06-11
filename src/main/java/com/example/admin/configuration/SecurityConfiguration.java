package com.example.admin.configuration;

import com.example.admin.repository.OrderRepository;
import jakarta.transaction.Synchronization;
import org.hibernate.annotations.Synchronize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;

@Configuration
public class SecurityConfiguration {
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
