package com.jason.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class AdditionalResourceWebConfiguration implements WebMvcConfigurer {

    String myExternalFilePath = "file:C:/Users/jpg74/IdeaProjects/jason/src/main/upload/";

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations(myExternalFilePath);
    }

}
