package com.example.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class GeminiConfig {
    @Value("${gemini.api-url}")
    private String apiUrl;

    @Value("${gemini.api-key}")
    private String apiKey;
}
