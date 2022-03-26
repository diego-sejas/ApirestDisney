package com.example.challengeAlkemy.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

        @Value("1.0")
        private String appVersion;

        @Value("Api Rest Disney")
        private String appName;

        @Value("Api crud con Roles")
        private String appDescription;

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()//
                                .info(getInfo())//
                                .externalDocs(new ExternalDocumentation()//
                                                .description("Ver proyecto en GitHub")//
                                                .url("https://github.com/diego-sejas/ApirestDisney"))
                                .components(new Components()//
                                                .addSecuritySchemes("JWT", new SecurityScheme()//
                                                                .type(SecurityScheme.Type.HTTP)//
                                                                .scheme("bearer")//
                                                                .bearerFormat("JWT")//
                                                                .in(SecurityScheme.In.HEADER)//
                                                                .name("Authorization")//
                                                                .description("Ejemplo: Bearer TOKEN")))//
                                .addSecurityItem(new SecurityRequirement()//
                                                .addList("JWT", Arrays.asList("read", "write")));
        }

        private Info getInfo() {
                return new Info()//
                                .version(appVersion)//
                                .title(appName)//
                                .description(appDescription)//
                                .contact(new Contact()//
                                                .name("DiegoSejas")//
                                                .url("https://www.linkedin.com/in/diego-sejas/")//
                                                .email("dsejas2015@gmail.com"))//
                                .license(new License()//
                                                .name("MIT License")//
                                                .url("http://opensource.org/licenses/MIT"));
        }

}
