package com.kursova.travel.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kursova.travel.constants.Constant;
import com.kursova.travel.security.filter.JwtAuthorizationFilter;
import com.kursova.travel.security.handler.JwtFailureHandler;
import com.kursova.travel.security.handler.JwtSuccessHandler;
import com.kursova.travel.service.web.JwtWebService;
import com.kursova.travel.service.PermissionService;
import com.kursova.travel.service.UserDetailsServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class    SecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsServiceImpl userDetailsService;
    PermissionService permissionService;
    JwtWebService jwtWebService;
    HostConfig hostConfig;
    ObjectMapper objectMapper;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .antMatcher("/api/**").authorizeRequests().anyRequest().authenticated()

                .and()
                .addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)

                .csrf()
                .disable();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(Constant.API_V1_PREFIX + "auth/**")
                .antMatchers(HttpMethod.GET, "/actuator/health")        // Ping back-end health

                .antMatchers(HttpMethod.OPTIONS, "/**")

                .antMatchers("/swagger-ui.html");                       // Swagger
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * @see <a href="https://docs.spring.io/spring-security/site/docs/5.0.3.RELEASE/reference/htmlsingle/#pe-dpe">Delegating Password Encoder</a>
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    private AbstractAuthenticationProcessingFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(
                jwtWebService,
                permissionService,
                new JwtSuccessHandler(),
                new JwtFailureHandler(objectMapper, hostConfig)
        );
    }
}
