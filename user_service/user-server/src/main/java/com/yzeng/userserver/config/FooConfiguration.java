package com.yzeng.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import feign.Feign;
import xyz.yzblog.weather.client.hystric.WeatherClientHystric;

@Configuration
public class FooConfiguration {
	@Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }
    
    @Bean
    public WeatherClientHystric fb(){
        return new WeatherClientHystric();
    }
}
