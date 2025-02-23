package io.reflectoring.Sprint3SpringBoot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * Config file for CORS settings
 */
@Configuration
public class CorsConfig {

    /**
     * Method to configure CORS
     * @return filter for CORS settings
     */
    @Bean
    public CorsFilter corsFilter() {
        // Creates a CORS configuration object
        CorsConfiguration config = new CorsConfiguration();

        // Configures the allowed origins
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        // Configures the allowed methods
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));

        // Configures the allowed headers
        config.setAllowedHeaders(List.of("*"));

        // Configures whether credentials are allowed
        config.setAllowCredentials(true);

        // Creates a CORS configuration source
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        // Registers the CORS configuration
        source.registerCorsConfiguration("/**", config);

        // Returns a new CORS filter
        return new CorsFilter(source);
    }
}