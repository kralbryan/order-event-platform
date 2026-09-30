package com.mehmet.order_event_platform.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Event Platform API")
                        .version("v1.0")
                        .description("Sipariş yönetimi ve olay güdümlü (event-driven) arka plan mimarisi API dokümantasyonu")
                        .contact(new Contact()
                                .name("Mehmet")
                                .email("mehmet@example.com")));
    }
}