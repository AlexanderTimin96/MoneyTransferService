package ru.netology.moneyTransferService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Value("${cross.origin.host.name}")
    private String origins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/transfer")
                .allowedOrigins(origins)
                .allowedMethods("POST");

        registry.addMapping("/confirmOperation")
                .allowedOrigins(origins)
                .allowedMethods("POST");
    }
}

