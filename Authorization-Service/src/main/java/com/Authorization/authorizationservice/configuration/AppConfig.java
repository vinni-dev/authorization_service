package com.Authorization.authorizationservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.Authorization.authorizationservice.annotation.AuthorizationAspect;
import com.Authorization.authorizationservice.annotation.LoggerAspect;



@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class AppConfig {
	@Bean
    public LoggerAspect notifyLogAspect() {
        return new LoggerAspect();
    }
	@Bean
    public AuthorizationAspect notifyAspect() {
        return new AuthorizationAspect();
    }
	
}
