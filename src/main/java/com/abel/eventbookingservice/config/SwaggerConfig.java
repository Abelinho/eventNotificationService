package com.abel.eventbookingservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private static final String SCHEME_NAME = "APIKey";
    private static final String SCHEME = "Bearer Token";

    @Bean
    public OpenAPI openAPI() {

        OpenAPI openApi = new OpenAPI()
                .info(apiInfo());
        addSecurity(openApi);
        return openApi;
    }

    private Info apiInfo() {
        return new Info()
                .title("Event notification Service - Musala Soft")
                .description("Event notification Service")
                .version("1.0")
                .license(new License()
                        .url("https://www.musalasoft.com")
                        .name("Musalasoft license"))
                .contact(new Contact()
                        .name("Abel Agbachi")
                        .email("mailto:agbachiabel@gmail.com")
                        .url("https://www.musalasoft.com")

                );
    }

    private void addSecurity(OpenAPI openApi) {
        Components components = createComponents();
        SecurityRequirement securityItem = new SecurityRequirement();
        securityItem.addList(SCHEME);


        openApi.components(components)
                .addSecurityItem(securityItem);
        openApi.security(Collections.singletonList(new SecurityRequirement().
                addList(SCHEME)));
        //  .addList(TIMESTAMP)));

    }

    private Components createComponents() {
        Components components = new Components();
        components.addSecuritySchemes(SCHEME, createSecurityScheme(SCHEME));
        return components;
    }

    private SecurityScheme createSecurityScheme(String name) {
        return new SecurityScheme()
                .name(name)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);
    }

}
