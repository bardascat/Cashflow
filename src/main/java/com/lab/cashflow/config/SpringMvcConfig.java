package com.lab.cashflow.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebMvc
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/ui/**").addResourceLocations("/angular/");
	}
	
	@Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        
        final ObjectMapper objectMapper = new ObjectMapper();
        converter.setObjectMapper(new ObjectMapper());
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
       
        
        converters.add(converter);
        super.configureMessageConverters(converters);
    }
	
	
}
