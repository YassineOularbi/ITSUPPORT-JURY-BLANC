package com.itsupport.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:/C:/Users/user/Desktop/ENAA-CHALLENGES-WORKSHOP-PROJECT/BRIEFS-ENAA-SIMPLONE/ITSUPPORT-JURY-BLANC/itsupport-backend/src/main/resources/static/images/");
    }
}
