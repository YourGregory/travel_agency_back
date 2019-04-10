package com.kursova.travel.config;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration bean to determine URL environments from property files.
 */
@Data
@Configuration
@ConfigurationProperties(value = "travel.security")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SecurityPropertiesConfig {

    JWTKeys jwt;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @ConfigurationProperties("travel.security.jwt")
    public static final class JWTKeys {

        String privateKey;

    }

}
