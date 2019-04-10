package com.kursova.travel;

import com.kursova.travel.config.HostConfig;
import com.kursova.travel.config.SecurityPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {
        HostConfig.class,
        SecurityPropertiesConfig.class,
        SecurityPropertiesConfig.JWTKeys.class,
})
@SpringBootApplication
public class TravelApplication {

    public static void main(String[] args) {
        SpringApplication.run(TravelApplication.class, args);
    }
}
