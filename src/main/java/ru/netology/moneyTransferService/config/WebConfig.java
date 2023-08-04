package ru.netology.moneyTransferService.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/transfer")
                .allowedOrigins("https://serp-ya.github.io")
                .allowedMethods("POST");

        registry.addMapping("/confirmOperation")
                .allowedOrigins("https://serp-ya.github.io")
                .allowedMethods("POST");
    }
}

