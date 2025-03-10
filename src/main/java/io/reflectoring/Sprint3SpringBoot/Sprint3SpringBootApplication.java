package io.reflectoring.Sprint3SpringBoot;

import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class Sprint3SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(Sprint3SpringBootApplication.class, args);
    }
}



