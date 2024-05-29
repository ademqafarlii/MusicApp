package com.example.musicApp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class DocumentConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Music App")
                        .version("0.1.0")
                        .description("Created for sharing and listen to music")
                        .contact(new Contact()
                                .email("ademqafarli88@gmail.com")
                                .url("www.linkedin.com/in/adem-qafarli-653913295")
                                .name("Adem Qafarli"))
        );
    }
}
