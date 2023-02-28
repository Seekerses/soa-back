package com.example.soa.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenApi(@Value("SOA lab work. Wev service #1 API Definition.")String appDescription,
                                 @Value("1.0")String appVersion) {
        return new OpenAPI().info(new Info().title("Starship service API")
                        .version(appVersion)
                        .description(appDescription)
                        .license(new License().name("not licensed yet")
                                .url("http://localhost/"))
                        .contact(new Contact().name("Daniil \"Seekerses\" Popov")
                                .email("seekergodlike@gmail.com")))
                .servers(List.of(new Server().url("http://localhost:8080")
                        .description("Main server")));
    }
}
