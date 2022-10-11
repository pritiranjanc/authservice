package com.verlab.alexandria.config;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Data
@Configuration
@ConfigurationProperties(prefix = "auth")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthConfig {
	
    private String domain;
    
}