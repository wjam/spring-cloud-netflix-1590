package com.example;

import feign.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class DemoApplication {

    @Bean
    public Logger.Level feignLoggingLevel() {
        return Logger.Level.FULL;
    }

    public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
