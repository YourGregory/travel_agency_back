package com.kursova.travel.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * Configuration bean to determine URL environments from property files.
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties("travel.host")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HostConfig {

    String backend;
    String ui;

    public String[] getAllowedCorsOrigins() {
        return List.of(ui).toArray(new String[NumberUtils.INTEGER_ZERO]);
    }

    public String[] getAllowedCorsMethods() {
        return List
                .of(
                        HttpMethod.PATCH,
                        HttpMethod.GET,
                        HttpMethod.POST,
                        HttpMethod.DELETE,
                        HttpMethod.OPTIONS,
                        HttpMethod.PUT
                )
                .stream()
                .map(HttpMethod::name)
                .toArray(String[]::new);
    }

}
