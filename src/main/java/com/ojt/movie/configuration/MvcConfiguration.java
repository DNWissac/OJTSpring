package com.ojt.movie.configuration;

import com.ojt.movie.util.HtmlCharacterEscapes;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    private final ObjectMapper objectMapper;

    @Bean
    public HttpMessageConverter<?> htmlEscapingConverter() {
        objectMapper.getFactory().setCharacterEscapes(new HtmlCharacterEscapes());
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }

    private String connectPath = "/img/**";
    private String resourcePath = "file:///C:/uploadImage/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(connectPath)
                .addResourceLocations(resourcePath);

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");
    }
}


