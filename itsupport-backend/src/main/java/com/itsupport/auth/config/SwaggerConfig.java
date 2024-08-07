package com.itsupport.auth.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Configuration class for Swagger/OpenAPI documentation.
 *
 * This class sets up the OpenAPI documentation with security requirements
 * and API metadata.
 *
 * Created by Yassine Oularbi
 *
 * Contact:
 * Email: yassineoularbi4@gmail.com
 * GitHub: @YassineOularbi
 */
@Configuration
public class SwaggerConfig {

    /**
     * Configures Swagger 2 for API documentation.
     *
     * This method creates a {@link Docket} bean that configures Swagger to
     * generate API documentation. It specifies the base package to scan for
     * API endpoints and includes all paths in the documentation.
     *
     * @return a {@link Docket} object configured for Swagger 2 documentation.
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.itsupport"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * Configures the OpenAPI documentation.
     *
     * This method creates an {@link OpenAPI} bean that sets up the OpenAPI
     * documentation with security requirements and API metadata.
     *
     * @return the configured {@link OpenAPI} object.
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
                .components(new Components().addSecuritySchemes("Bearer Authentication", createAPIKeyScheme()))
                .info(apiInfo());
    }

    /**
     * Creates the security scheme for the API.
     *
     * This method configures the security scheme for Bearer token authentication,
     * using JWT as the bearer format.
     *
     * @return the configured {@link SecurityScheme} object.
     */
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }

    /**
     * Provides metadata information about the API.
     *
     * This method configures the API metadata such as title, version, description,
     * and contact information.
     *
     * @return the configured {@link Info} object.
     */
    private Info apiInfo() {
        return new Info()
                .title("Authentication Service API Documentation")
                .version("1.0.0")
                .description("HTTP APIs to manage user registration and authentication.")
                .contact(new Contact().name("Yassine OULARBI").email("yassineoularbi4@gmail.com").url("https://github.com/YassineOularbi"));
    }
}
