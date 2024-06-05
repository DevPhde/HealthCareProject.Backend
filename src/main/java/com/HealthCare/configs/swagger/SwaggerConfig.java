package com.HealthCare.configs.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("HealthCare API")
                        .description("SpringBoot Application")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Azul Ciano")
                                .email("")));
    }
}
